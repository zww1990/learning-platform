package com.cfilmcloud.collate.orm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TSysTaskCheckResultCommon {
	@Id
	@Column(name = "row_id")
	private Integer rowId;
	@Column(name = "batch_no")
	private String batchNo;
	@Column(name = "task_no")
	private String taskNo;
	@Column(name = "check_date_begin")
	private Date checkDateBegin;
	@Column(name = "check_date_end")
	private Date checkDateEnd;
	@Column(name = "check_type")
	private Integer checkType;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "dim_list")
	private String dimList;
	@Column(name = "fact_list")
	private String factList;
	@Column(name = "source_dim_list")
	private String sourceDimList;
	@Column(name = "source_fact_list")
	private String sourceFactList;
	@Column(name = "target_dim_list")
	private String targetDimList;
	@Column(name = "target_fact_list")
	private String targetFactList;

	public TSysTaskCheckResultCommon() {
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public Date getCheckDateBegin() {
		return checkDateBegin;
	}

	public void setCheckDateBegin(Date checkDateBegin) {
		this.checkDateBegin = checkDateBegin;
	}

	public Date getCheckDateEnd() {
		return checkDateEnd;
	}

	public void setCheckDateEnd(Date checkDateEnd) {
		this.checkDateEnd = checkDateEnd;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDimList() {
		return dimList;
	}

	public void setDimList(String dimList) {
		this.dimList = dimList;
	}

	public String getFactList() {
		return factList;
	}

	public void setFactList(String factList) {
		this.factList = factList;
	}

	public String getSourceDimList() {
		return sourceDimList;
	}

	public void setSourceDimList(String sourceDimList) {
		this.sourceDimList = sourceDimList;
	}

	public String getSourceFactList() {
		return sourceFactList;
	}

	public void setSourceFactList(String sourceFactList) {
		this.sourceFactList = sourceFactList;
	}

	public String getTargetDimList() {
		return targetDimList;
	}

	public void setTargetDimList(String targetDimList) {
		this.targetDimList = targetDimList;
	}

	public String getTargetFactList() {
		return targetFactList;
	}

	public void setTargetFactList(String targetFactList) {
		this.targetFactList = targetFactList;
	}
}
