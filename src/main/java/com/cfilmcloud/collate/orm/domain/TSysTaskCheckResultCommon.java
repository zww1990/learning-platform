package com.cfilmcloud.collate.orm.domain;

import java.util.Date;

public class TSysTaskCheckResultCommon {
	private String batchNo;
	private String taskNo;
	private Date checkDateBegin;
	private Date checkDateEnd;
	private Integer checkType;
	private Date createTime;
	private String dimList;
	private String factList;
	private String sourceDimList;
	private String sourceFactList;
	private String targetDimList;
	private String targetFactList;

	public TSysTaskCheckResultCommon() {
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
