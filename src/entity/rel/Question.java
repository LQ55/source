package entity.rel;

import q.base.annotation.Column;


@q.base.annotation.Entity(DBTName="question",label="问卷问题实体",conment="问题信息")
public class Question {
	
	@Column(DBColumnName="quesnireId",label="问题ID")
	public Integer quesnireId;
	
	@Column(DBColumnName="quesContent",length=8000,label="问题内容")
	public String quesContent;
	
	
	@Column(DBColumnName="isMultiSelect",nullable=true,label="是否是多选")
	public String isMultiSelect;

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
	
}
