package com.lqcool.qbase.base.util;

import com.lqcool.qbase.base.annotation.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述实体映射的数据库表信息，如果实体没有配置指定注解，将不会对实体进行持久化。这里的parse将返回null值
 * @author Q 2017-05-17 02:41:14
 * 
 */
public class DBTableInfo {
	private String DBTableName;
	private Class<?> clazz;
	private boolean needPersist=false;//是否需要持久化存储
	private Map<String, DBColumnInfo> DBColumnInfos = new HashMap<String, DBColumnInfo>();
	//存放该表中所有需要持久化的字段的信息
	public DBTableInfo parse(Class<?> clazz){
		this.clazz = clazz;
		this.DBTableName = this.clazz.getSimpleName();//初始化实体名默认作为表名
		Annotation [] annotations = this.clazz.getAnnotations();
		if(!(annotations.length == 0)){
			for(Annotation annotation : annotations){
				if(annotation.annotationType().equals(Entity.class)){//包含@Entity注解，表明此实体需要持久化存储
					this.needPersist=true;//设置持久化标记
					Entity entity = (Entity)annotation;
					if(!entity.DBTName().equals("")){
						this.DBTableName=entity.DBTName();//重新设置映射表名
					}
				}
			}
		}
		else{
			System.out.println("[警告]--------实体{" + DBTableName +"}未配置注解");
			this.needPersist=false;
		}
		if(this.needPersist){
			Field[] fields = this.clazz.getDeclaredFields();
			for(Field field:fields){
				DBColumnInfo dbColumnInfo = new DBColumnInfo();
				dbColumnInfo = dbColumnInfo.parse(field);
				if(dbColumnInfo != null){
					this.DBColumnInfos.put(field.getName(), dbColumnInfo);
				}
			}
			return this;
		}
		else {
			return null;
		}
	}
	
	/**
	 * @return String 返回的是建立表的语句
	 */
	@Override
	public String toString(){
		if(this.needPersist){
			int i = 0;
			StringBuilder sql = new StringBuilder();
			sql.append(Symbol.BLANK);
			sql.append("CREATE TABLE ");
			sql.append(this.DBTableName + Symbol.BLANK);
			sql.append("(");
			for(DBColumnInfo dbColumnInfo:this.DBColumnInfos.values()){
				i++;
				if(i<this.DBColumnInfos.size()){
					sql.append(Symbol.LINE);
					sql.append(Symbol.TAB);
					sql.append(dbColumnInfo.toString());
					sql.append(",");
				}
				else{
					sql.append(Symbol.LINE);
					sql.append(Symbol.TAB);
					sql.append(dbColumnInfo.toString());
				}
			}
			
			sql.append(Symbol.LINE);
			sql.append(Symbol.BLANK);
			sql.append(");");
			return sql.toString();
		}
		else {
			return null;
		}
	}

	/**
	 * 获取数据库表名
	 * @return
	 */
	public String getDBTableName() {
		return DBTableName;
	}

	/**
	 * 设置数据库表名
	 * @param dBTableName
	 */
	public void setDBTableName(String dBTableName) {
		DBTableName = dBTableName;
	}

	/**
	 * 获取数数据库字段信息
	 * @return
	 */
	public Map<String, DBColumnInfo> getDBColumnInfos() {
		return DBColumnInfos;
	}

	/**
	 * 设置数据库字段信息
	 * @param dBColumnInfos
	 */
	public void setDBColumnInfos(Map<String, DBColumnInfo> dBColumnInfos) {
		DBColumnInfos = dBColumnInfos;
	}
	
	
}
