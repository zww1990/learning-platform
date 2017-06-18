package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

/**
 * TSysTaskCheckResult entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_task_check_result")
public class TSysTaskCheckResult implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String batchNo;
	private String taskNo;
	private Date checkDateBegin;
	private Date checkDateEnd;
	private Integer checkResult;
	private Integer diffRows;
	private Integer checkRows;
	private Date createTime;
	private String taskDesc;
	private String checkResultName;

	// Constructors

	/** default constructor */
	public TSysTaskCheckResult() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "batch_no", length = 38)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "task_no", length = 10)
	public String getTaskNo() {
		return this.taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "check_date_begin", length = 10)
	public Date getCheckDateBegin() {
		return this.checkDateBegin;
	}

	public void setCheckDateBegin(Date checkDateBegin) {
		this.checkDateBegin = checkDateBegin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "check_date_end", length = 10)
	public Date getCheckDateEnd() {
		return this.checkDateEnd;
	}

	public void setCheckDateEnd(Date checkDateEnd) {
		this.checkDateEnd = checkDateEnd;
	}

	@Column(name = "check_result")
	public Integer getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	@Column(name = "diff_rows")
	public Integer getDiffRows() {
		return this.diffRows;
	}

	public void setDiffRows(Integer diffRows) {
		this.diffRows = diffRows;
	}

	@Column(name = "check_rows")
	public Integer getCheckRows() {
		return this.checkRows;
	}

	public void setCheckRows(Integer checkRows) {
		this.checkRows = checkRows;
	}

	@Column(name = "create_time", length = 19, updatable = false)
	@CreationTimestamp
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@Transient
	public String getCheckResultName() {
		return checkResultName;
	}

	public void setCheckResultName(String checkResultName) {
		this.checkResultName = checkResultName;
	}

}