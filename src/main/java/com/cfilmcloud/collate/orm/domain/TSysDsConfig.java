package com.cfilmcloud.collate.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * TSysDsConfig entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_ds_config", uniqueConstraints = @UniqueConstraint(columnNames = "DATA_SOURCE_ID"))
public class TSysDsConfig implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String dataSourceId;
	private String dataSourceName;
	private String receiveMethod;
	private Integer isEnable;
	private String dataSourceDescribe;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;
	private String methodName;
	private String enableName;

	// Constructors

	/** default constructor */
	public TSysDsConfig() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROW_ID", unique = true, nullable = false)
	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "DATA_SOURCE_ID", unique = true, length = 20)
	public String getDataSourceId() {
		return this.dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	@Column(name = "DATA_SOURCE_NAME", length = 80)
	public String getDataSourceName() {
		return this.dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	@Column(name = "RECEIVE_METHOD", length = 50)
	public String getReceiveMethod() {
		return this.receiveMethod;
	}

	public void setReceiveMethod(String receiveMethod) {
		this.receiveMethod = receiveMethod;
	}

	@Column(name = "IS_ENABLE")
	public Integer getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "DATA_SOURCE_DESCRIBE", length = 120)
	public String getDataSourceDescribe() {
		return this.dataSourceDescribe;
	}

	public void setDataSourceDescribe(String dataSourceDescribe) {
		this.dataSourceDescribe = dataSourceDescribe;
	}

	@Column(name = "IS_DELETE")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "DELETE_TIME", length = 19)
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name = "CREATE_TIME", nullable = false, length = 19, updatable = false)
	@CreationTimestamp
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 19)
	@UpdateTimestamp
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CREATER", length = 50)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "UPDATER", length = 50)
	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Transient
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Transient
	public String getEnableName() {
		return enableName;
	}

	public void setEnableName(String enableName) {
		this.enableName = enableName;
	}

}