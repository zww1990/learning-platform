package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSysTaskBatchLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_task_batch_log")
public class TSysTaskBatchLog implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String batchNo;
	private String taskNo;
	private String checkDate;
	private String execStep;
	private Integer execState;
	private String errorInfo;
	private Integer execType;
	private Integer logLevel;
	private String execSql;
	private Date beginTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public TSysTaskBatchLog() {
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

	@Column(name = "batch_no", length = 36)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "task_no", length = 8)
	public String getTaskNo() {
		return this.taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Column(name = "check_date", length = 120)
	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "exec_step", length = 120)
	public String getExecStep() {
		return this.execStep;
	}

	public void setExecStep(String execStep) {
		this.execStep = execStep;
	}

	@Column(name = "exec_state")
	public Integer getExecState() {
		return this.execState;
	}

	public void setExecState(Integer execState) {
		this.execState = execState;
	}

	@Column(name = "error_info", length = 65535)
	public String getErrorInfo() {
		return this.errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Column(name = "exec_type")
	public Integer getExecType() {
		return this.execType;
	}

	public void setExecType(Integer execType) {
		this.execType = execType;
	}

	@Column(name = "log_level")
	public Integer getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	@Column(name = "exec_sql", length = 65535)
	public String getExecSql() {
		return this.execSql;
	}

	public void setExecSql(String execSql) {
		this.execSql = execSql;
	}

	@Column(name = "begin_time", length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}