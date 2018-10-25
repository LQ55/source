package com.lqcool.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import q.base.annotation.Column;
import q.base.persistence.baseEntity.Entity;
import q.base.persistence.baseEntity.EntityClass;
import q.base.util.dbconnect.DBDao;


@q.base.annotation.Entity(DBTName="question",label="问卷问题实体",conment="问题信息")
public class Question extends Entity{
	
	
	@Column(DBColumnName="quesContent",length=8000,label="问题内容")
	public String quesContent;
	
	
	@Column(DBColumnName="isMultiSelect",nullable=true,label="是否是多选")
	public String isMultiSelect;

	@Column(DBColumnName="quesnireId",label="问题ID")
	public Integer quesnireId;
	
	public String getQuesContent() {
		return quesContent;
	}

	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}

	public String getIsMultiSelect() {
		return isMultiSelect;
	}

	public void setIsMultiSelect(String isMultiSelect) {
		this.isMultiSelect = isMultiSelect;
	}

	public Integer getQuesnireId() {
		return quesnireId;
	}

	public void setQuesnireId(Integer quesnireId) {
		this.quesnireId = quesnireId;
	}
	
	public static void main(String args []){
		Class<Question> clazz = Question.class;
		Question dd = new Question();
		dd.isMultiSelect="fdjfkdljfkd";
		dd.quesContent="ddddddddd";
		dd.quesnireId = 23;
		try {
			String sql = EntityClass.createInsertSQL(clazz);
			Connection con = DBDao.getConnction();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			PreparedStatement pre = EntityClass.fillInsertParameters(preparedStatement, dd);
			System.out.println(pre);
/*			Field [] fields = clazz.getDeclaredFields();
			
			for(int i = 0; i < fields.length; i ++){
				String methodName = "get" + (fields[i].getName().charAt(0) + "").toUpperCase() + fields[i].getName().substring(1);
				
				Method method = clazz.getDeclaredMethod(methodName);
				Object values = method.invoke(dd, new Object[]{});
				System.out.println(values);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
