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
 * TSysDsConfigItem entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_ds_config_item")
public class TSysDsConfigItem implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String dataSourceId;
	private String columnName;
	private String columnDescribe;
	private String colunmType;
	private String colunmLength;
	private String dateFormat;
	private Integer colunmOrder;
	private Integer columnClass;
	private Integer isDelete;
	private Date deteleTime;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;

	// Constructors

	/** default constructor */
	public TSysDsConfigItem() {
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

	@Column(name = "DATA_SOURCE_ID", length = 20)
	public String getDataSourceId() {
		return this.dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	@Column(name = "COLUMN_NAME", length = 80)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "COLUMN_DESCRIBE", length = 120)
	public String getColumnDescribe() {
		return this.columnDescribe;
	}

	public void setColumnDescribe(String columnDescribe) {
		this.columnDescribe = columnDescribe;
	}

	@Column(name = "COLUNM_TYPE", length = 50)
	public String getColunmType() {
		return this.colunmType;
	}

	public void setColunmType(String colunmType) {
		this.colunmType = colunmType;
	}

	@Column(name = "COLUNM_LENGTH", length = 50)
	public String getColunmLength() {
		return this.colunmLength;
	}

	public void setColunmLength(String colunmLength) {
		this.colunmLength = colunmLength;
	}

	@Column(name = "DATE_FORMAT", length = 50)
	public String getDateFormat() {
		return this.dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Column(name = "COLUNM_ORDER")
	public Integer getColunmOrder() {
		return this.colunmOrder;
	}

	public void setColunmOrder(Integer colunmOrder) {
		this.colunmOrder = colunmOrder;
	}

	@Column(name = "COLUMN_CLASS")
	public Integer getColumnClass() {
		return this.columnClass;
	}

	public void setColumnClass(Integer columnClass) {
		this.columnClass = columnClass;
	}

	@Column(name = "IS_DELETE")
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "DETELE_TIME", length = 19)
	public Date getDeteleTime() {
		return this.deteleTime;
	}

	public void setDeteleTime(Date deteleTime) {
		this.deteleTime = deteleTime;
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

}