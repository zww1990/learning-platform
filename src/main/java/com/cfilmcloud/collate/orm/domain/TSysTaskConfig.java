package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * TSysTaskConfig entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_task_config")
public class TSysTaskConfig implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String taskNo;
	private String taskDesc;
	private String sourceSql;
	private String targetSql;
	private String sourceDataSourceId;
	private String targetDataSourceId;
	private String paramName;
	private String paramDesc;
	private String dimName;
	private String factName;
	private String dimDesc;
	private String factDesc;
	private Integer isEnable;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public TSysTaskConfig() {
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

	@Column(name = "task_no", length = 8)
	public String getTaskNo() {
		return this.taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Column(name = "task_desc", length = 260)
	public String getTaskDesc() {
		return this.taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@Column(name = "source_sql", length = 65535)
	public String getSourceSql() {
		return this.sourceSql;
	}

	public void setSourceSql(String sourceSql) {
		this.sourceSql = sourceSql;
	}

	@Column(name = "target_sql", length = 65535)
	public String getTargetSql() {
		return this.targetSql;
	}

	public void setTargetSql(String targetSql) {
		this.targetSql = targetSql;
	}

	@Column(name = "source_data_source_id", length = 20)
	public String getSourceDataSourceId() {
		return this.sourceDataSourceId;
	}

	public void setSourceDataSourceId(String sourceDataSourceId) {
		this.sourceDataSourceId = sourceDataSourceId;
	}

	@Column(name = "target_data_source_id", length = 20)
	public String getTargetDataSourceId() {
		return this.targetDataSourceId;
	}

	public void setTargetDataSourceId(String targetDataSourceId) {
		this.targetDataSourceId = targetDataSourceId;
	}

	@Column(name = "param_name", length = 120)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "param_desc", length = 120)
	public String getParamDesc() {
		return this.paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	@Column(name = "dim_name", length = 120)
	public String getDimName() {
		return this.dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	@Column(name = "Fact_name", length = 120)
	public String getFactName() {
		return this.factName;
	}

	public void setFactName(String factName) {
		this.factName = factName;
	}

	@Column(name = "dim_desc", length = 120)
	public String getDimDesc() {
		return this.dimDesc;
	}

	public void setDimDesc(String dimDesc) {
		this.dimDesc = dimDesc;
	}

	@Column(name = "Fact_desc", length = 120)
	public String getFactDesc() {
		return this.factDesc;
	}

	public void setFactDesc(String factDesc) {
		this.factDesc = factDesc;
	}

	@Column(name = "is_enable", nullable = false)
	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "is_delete", nullable = false)
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "delete_time", length = 19)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "create_time", length = 19, updatable = false)
	@CreationTimestamp
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	@UpdateTimestamp
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}