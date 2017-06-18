package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String taskDesc;
	private String execStateName;
	private String execTypeName;

	// Constructors

	/** default constructor */
	public TSysTaskBatchLog() {
	}

	/**
	 * @param batchNo
	 *            批次号
	 * @param taskNo
	 *            任务编号
	 * @param checkDate
	 *            对账日期
	 * @param execStep
	 *            执行步骤
	 * @param execState
	 *            执行状态
	 * @param logLevel
	 *            日志级别
	 * @param endTime
	 *            结束时间
	 * @param beginTime
	 *            开始时间
	 * @param errorInfo
	 *            错误信息
	 */
	public TSysTaskBatchLog(String batchNo, String taskNo, String checkDate, String execStep, Integer execState,
			Integer logLevel, Date endTime, Date beginTime, String errorInfo) {
		this.batchNo = batchNo;
		this.taskNo = taskNo;
		this.checkDate = checkDate;
		this.execStep = execStep;
		this.execState = execState;
		this.logLevel = logLevel;
		this.endTime = endTime;
		this.beginTime = beginTime;
		this.errorInfo = errorInfo;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 19)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Transient
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@Transient
	public String getExecStateName() {
		return execStateName;
	}

	public void setExecStateName(String execStateName) {
		this.execStateName = execStateName;
	}

	@Transient
	public String getExecTypeName() {
		return execTypeName;
	}

	public void setExecTypeName(String execTypeName) {
		this.execTypeName = execTypeName;
	}

}