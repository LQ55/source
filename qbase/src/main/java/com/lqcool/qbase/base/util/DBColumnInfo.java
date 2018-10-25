package com.lqcool.qbase.base.util;

import com.lqcool.qbase.base.annotation.Column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;


/**
 * 该类定义一些属性用于描述字段信息
 * @author Q 2017-05-17 01:29:19
 *
 */
public class DBColumnInfo {
	private String columnName;//字段名
	
	private Class<?> type;//字段类型
	
	private boolean isPrimaryKey = false;//是否为主键
	
	private boolean identity = false;//是否自增
	
	private boolean nullable=true;//是否可以为空
	
	private boolean isunique=false;
	
	private int length=32;//字段长度
	
	private boolean needPersist=false;//字段是否需要持久化
	
	
	
	
	public DBColumnInfo parse(Field field){
		
		this.columnName = field.getName();//设置字段映射到数据库的名称
		
		this.type = field.getType();
		
		Annotation[] annotations = field.getAnnotations();
		
		for(Annotation annotation:annotations){
			
			if(annotation.annotationType().equals(Column.class)){//有Column注解
				
				this.needPersist = true;//需要持久化字段到数据库
				
				Column column = (Column)annotation;
				
				if(!column.DBColumnName().equals("")){//字段名不为空
					
					this.columnName = column.DBColumnName();//从新设置映射到数据库字段名称
					
				}
				
				this.nullable=column.nullable();
				
				if(column.length()!=-1){//若字段长度不为空，设置字段长度
					
					this.length = column.length();
					
				}
				
				this.isPrimaryKey = column.isprimarykey();//是否主键
				
				this.identity = column.isidentity();//是否自增
				
				this.isunique = column.isunique();
				
			}
			else{
				/**
				 * 后续处理 2017-05-17 01:45:48
				 */
			}
		}
		
		if(this.needPersist){
			return this;
		}
		else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sql = new StringBuilder(columnName);
		if(this.type.equals(String.class)){
			sql.append(Symbol.BLANK + "VARCHAR(" + this.length + ")");
		}
		else if(this.type.equals(Integer.class) || this.type.equals(int.class)){
			sql.append(Symbol.BLANK + "INT");//int没有设置长度，所以length只有在String类型时起作用
		}
		else if(this.type.equals(Long.class) || this.type.equals(long.class)){
			sql.append(Symbol.BLANK + "BIGINT");
		}
		else if(this.type.equals(Double.class) || this.type.equals(double.class)){
			sql.append(Symbol.BLANK + "FLOAT");
		}
		else if(this.type.equals(Float.class) || this.type.equals(float.class)){
			sql.append(Symbol.BLANK + "FLOAT");
		}
		else if(this.type.equals(Date.class)){
			sql.append(Symbol.BLANK + "DATE");
		}
		
		if(this.isPrimaryKey){
			sql.append(Symbol.BLANK + "PRIMARY KEY");
		}
		
		if(this.identity){
			sql.append(Symbol.BLANK + "AUTO_INCREMENT");
		}
		
		if(this.isunique){
			sql.append(Symbol.BLANK + "UNIQUE");
		}
		
		if(!this.nullable){
			sql.append(Symbol.BLANK + "NOT NULL");
		}
		
		return sql.toString();
	}
	
	
}
