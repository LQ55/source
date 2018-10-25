package com.lqcool.qbase.base.persistence.baseEntity;

import com.lqcool.qbase.base.annotation.Column;

import java.io.Serializable;
import java.util.Date;


public abstract class Entity implements Serializable{
	/**
	 * 用于json序列化
	 */
	//private final static ThreadLocal<Boolean> lazyLoad = new ThreadLocal<Boolean>();
	
	@Column(DBColumnName="EID",isprimarykey=true,nullable=false,label="主键")
	private Long EID;
	
	@Column(DBColumnName="operateDate",label="操作日期")
	private  Date operateDate;
	
	@Column(DBColumnName="operatorCode",label="操作人编码",length=20)
	private  String operatorCode;
	
	@Column(DBColumnName="remark",length=8000,label="备注")
	private  String remark;
	
	/**
	 * 
	 * 重写hashCode方法
	 * System.identityHashCode(objct)，返回给定对象的哈希码，该代码与默认的hashCode()返回的代码一样，无论给定对象的类是否重写hashCode(),null引用的哈希码为0
	 * 该方法是根据对象在内存中的地址计算出来的值，如果对象不同，那么返回的计算结果不同。
	 * 
	 * 
	 * hashCode()是根据内容来产生hash值的。
	 * System.identityHashCode(object)，是根据内存地址来产生hash值的。
	 * 
	 * 
	 * @return int 返回hash码
	 * @author LQ 2017年6月14日01:57:55
	 */
	@Override
	public final int hashCode()
	{
		if(this.getEID()  == null)
		{
			return System.identityHashCode(this);
		}
		else
		{
			
			return this.getEID().hashCode();
		}
	}

	public Long getEID() {
		return EID;
	}

	public void setEID(Long EID) {
		this.EID = EID;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
/*	@Column(DBColumnName="inputCode",label="输入码",length=20)
	private  String inputCode;
	
	@Column(DBColumnName="orgId",label="单位主键")
	private  Long orgId;*/
	
	/**
	 * 标识该对象是否被初始化过
	 */
/*	private  boolean initialized = false;
	//是否是新创建的对像
	private  boolean newEntity = true;
	
	@Column(DBColumnName="attachmentCode",label="附件编码")
	private String attachmentCode;*/
	
	
}
