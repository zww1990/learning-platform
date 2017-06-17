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
 * TSysTaskConfigItemRel entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_task_config_item_rel")
public class TSysTaskConfigItemRel implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String taskNo;
	private String sourceDataSourceId;
	private String targetDataSourceId;
	private String sourceColumnName;
	private String sourceColumnDesc;
	private String sourceColumnTrans;
	private String targetColumnName;
	private String targetColumnDesc;
	private String targetColumnTrans;
	private Integer relType;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public TSysTaskConfigItemRel() {
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

	@Column(name = "source_data_source_id", length = 260)
	public String getSourceDataSourceId() {
		return this.sourceDataSourceId;
	}

	public void setSourceDataSourceId(String sourceDataSourceId) {
		this.sourceDataSourceId = sourceDataSourceId;
	}

	@Column(name = "target_data_source_id", length = 260)
	public String getTargetDataSourceId() {
		return this.targetDataSourceId;
	}

	public void setTargetDataSourceId(String targetDataSourceId) {
		this.targetDataSourceId = targetDataSourceId;
	}

	@Column(name = "source_column_name", length = 260)
	public String getSourceColumnName() {
		return this.sourceColumnName;
	}

	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}

	@Column(name = "source_column_desc", length = 260)
	public String getSourceColumnDesc() {
		return this.sourceColumnDesc;
	}

	public void setSourceColumnDesc(String sourceColumnDesc) {
		this.sourceColumnDesc = sourceColumnDesc;
	}

	@Column(name = "source_column_trans", length = 260)
	public String getSourceColumnTrans() {
		return this.sourceColumnTrans;
	}

	public void setSourceColumnTrans(String sourceColumnTrans) {
		this.sourceColumnTrans = sourceColumnTrans;
	}

	@Column(name = "target_column_name", length = 260)
	public String getTargetColumnName() {
		return this.targetColumnName;
	}

	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}

	@Column(name = "target_column_desc", length = 260)
	public String getTargetColumnDesc() {
		return this.targetColumnDesc;
	}

	public void setTargetColumnDesc(String targetColumnDesc) {
		this.targetColumnDesc = targetColumnDesc;
	}

	@Column(name = "target_column_trans", length = 260)
	public String getTargetColumnTrans() {
		return this.targetColumnTrans;
	}

	public void setTargetColumnTrans(String targetColumnTrans) {
		this.targetColumnTrans = targetColumnTrans;
	}

	@Column(name = "rel_type")
	public Integer getRelType() {
		return this.relType;
	}

	public void setRelType(Integer relType) {
		this.relType = relType;
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