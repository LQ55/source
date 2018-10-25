package com.lqcool.qbase.base.persistence.baseEntity;


import com.lqcool.qbase.base.annotation.Column;
import com.lqcool.qbase.base.util.Symbol;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


/**
 *
 * @author Q 2017-05-23 00:39:19
 *
 */
public class EntityClass <T extends Entity>{
	private Class<? extends Entity> clazz;
	private String insertSql;//插入语句
	private String deleteSql;//删除语句
	private String updateSql;//更新语句
	private String selectSql;//查询语句

	public EntityClass(Class<? extends Entity> clazz){
		this.clazz = clazz;
	}

	/**
	 * 创建插入语句
	 * @param clazz
	 * @return 插入语句
	 */
	public static String createInsertSQL(Class<?> clazz){
		StringBuilder insertSQLBuilder = new StringBuilder("INSERT IN TO ");
		//通过注解获取数据库表名
		int fieldSize = (clazz.getSuperclass().getDeclaredFields().length + clazz.getDeclaredFields().length);
		com.lqcool.qbase.base.annotation.Entity entityAnnotation = clazz.getAnnotation(com.lqcool.qbase.base.annotation.Entity.class);

		//如果没有配置表名，默认的表名就为实体的类名
		if(entityAnnotation.DBTName()==""){
			insertSQLBuilder.append(clazz.getSimpleName() + Symbol.BLANK);
		}
		else{
			insertSQLBuilder.append(entityAnnotation.DBTName() + Symbol.BLANK);
		}
		insertSQLBuilder.append(" (");
		insertSQLBuilder.append(getDBTColumnNames(clazz));
		insertSQLBuilder.append(") VALUES (");
		insertSQLBuilder.append(createInsertValues(fieldSize));
		insertSQLBuilder.append(")");
		return insertSQLBuilder.toString();
	}

	/**
	 * 获取字段信息
	 * @param clazz
	 * @return 字段的组合字符串
	 */
	public static String getDBTColumnNames(Class<?> clazz){
		String dbtfieldNames = "";
		//父类类型信息
		Class<?> superclazz = clazz.getSuperclass();
		Field [] superFields = superclazz.getDeclaredFields();
		//父类字段
		for(Field field:superFields){
			Column superColumn = field.getAnnotation(Column.class);
			if(superColumn.DBColumnName()==""){
				dbtfieldNames += field.getName();
				dbtfieldNames += ",";
			}
			else{
				dbtfieldNames += superColumn.DBColumnName();
				dbtfieldNames += ",";
			}
		}

		//子类字段
		Field [] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			Column subcolumn = field.getAnnotation(Column.class);
			if(subcolumn.DBColumnName()==""){
				dbtfieldNames += field.getName();
				dbtfieldNames += ",";
			}
			else{
				dbtfieldNames += subcolumn.DBColumnName();
				dbtfieldNames += ",";
			}
		}

		return dbtfieldNames.substring(0, dbtfieldNames.length()-1);
	}

	/**
	 * 获取占位符?
	 * @param fieldSize 字段的个数
	 * @return
	 */
	public static String createInsertValues(int fieldSize){
		String values = "";
		for(int i = 0; i < fieldSize; i ++){
			values += "?,";
		}
		return values.substring(0, values.length()-1);
	}

	/**
	 * 填充插入语句参数
	 * @param preparedStatement
	 * @param entity 实体信息
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	 */
	public static PreparedStatement fillInsertParameters(PreparedStatement preparedStatement,com.lqcool.qbase.base.persistence.baseEntity.Entity entity)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {

		//占位符起始下标
		int index = 1;
		Class<?> clazz = entity.getClass();
		//父类类型信息
		Class<?> superclazz = clazz.getSuperclass();
		Field [] superFields = superclazz.getDeclaredFields();
		for(Field field:superFields){
			//拼凑get方法字符串
			String methodStr = "get" + (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
			//得到访问器方法
			Method method = superclazz.getDeclaredMethod(methodStr);
			//通过反射获取属性值
			Object value = method.invoke(entity, new Object[]{});
			setPreparedStatement(preparedStatement,index,value);
			index++;
		}

		Field [] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			//拼凑get方法字符串
			String methodStr = "get" + (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
			//得到访问器方法
			Method method = clazz.getDeclaredMethod(methodStr);
			//通过反射获取属性值
			Object value = method.invoke(entity, new Object[]{});
			setPreparedStatement(preparedStatement,index,value);
			index++;
		}
		return preparedStatement;
	}

	/**
	 * 本方法用于设置prepareStatement中的参数
	 * @param preparedStatement 
	 * @param index 参数下标
	 * @param value 参数值
	 * @throws SQLException
	 */
	public static void setPreparedStatement(PreparedStatement preparedStatement,int index,Object value) throws SQLException{
		if(value!=null){
			Class clazz = value.getClass();
			//日期类型特别处理
			if(Date.class.equals(clazz)||java.sql.Date.class.equals(clazz)){
				value = new java.sql.Timestamp(((Date)value).getTime());
			}
		}
		preparedStatement.setObject(index, value);
	}

	/**
	 * 设置默认参数，如操作日期，EID，rmarks,操作编码
	 * @param entity
	 * @return
	 */
	public static Entity setDefaultPerameters(Entity entity){
		Long EID = entity.getEID();
		if(EID == null ){
			EID = System.currentTimeMillis();
			entity.setEID(EID);
		}

		Date opreateDate = entity.getOperateDate();
		if(opreateDate == null){
			opreateDate = new Date();
			entity.setOperateDate(opreateDate);
		}

		String operatorCode = entity.getOperatorCode();
		if(operatorCode == null){
			operatorCode = "L5646465445";
			entity.setOperatorCode(operatorCode);
		}
		return entity;

	}

	private static String createUpdateSQL(Class<?> clazz){
		StringBuffer updateSQL = new StringBuffer();
		updateSQL.append("UPDATE ");
		com.lqcool.qbase.base.annotation.Entity entityAnnotation = clazz.getAnnotation(com.lqcool.qbase.base.annotation.Entity.class);
		if(entityAnnotation.DBTName()==""){
			updateSQL.append(clazz.getSimpleName());
		}
		else {
			updateSQL.append(entityAnnotation.DBTName());
		}
		updateSQL.append(" WHERE 1=1 AND EID=?");
		
		return updateSQL.toString();
	}

	/**
	 * 创建删除语句
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String createDeleteSQL(Class<? extends Entity> clazz){
		StringBuffer deleteSQL = new StringBuffer();
		deleteSQL.append("DELETE FROM ");
		com.lqcool.qbase.base.annotation.Entity entityAnnotation = clazz.getAnnotation(com.lqcool.qbase.base.annotation.Entity.class);
		if(entityAnnotation.DBTName()==""){
			deleteSQL.append(clazz.getSimpleName());
		}
		else {
			deleteSQL.append(entityAnnotation.DBTName());
		}
		deleteSQL.append(" WHERE 1=1 AND EID=?"); 
		return deleteSQL.toString();
	}
	
	/**
	 * 填充删除语句
	 * @param preparedStatement
	 * @param entityId
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement fillDeleteSql(PreparedStatement preparedStatement,Long entityId) throws SQLException {
		preparedStatement.setObject(1, entityId);
		preparedStatement.addBatch();
		return preparedStatement;
	}

	/***********************************************修改器与访问器****************************************/
	public String getInsertSql() {
		return insertSql;
	}
	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
	public String getSelectSql() {
		return selectSql;
	}
	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	/*	public static void main(String [] args){
		Class<?> class1 = Question.class;
		System.out.println(createInsertSQL(class1));
	}
	 */
}
