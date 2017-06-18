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
 * TSysDsItemDict entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_ds_item_dict", uniqueConstraints = { @UniqueConstraint(columnNames = "COLUMN_NAME"),
		@UniqueConstraint(columnNames = "COLUMN_DESCRIBE") })
public class TSysDsItemDict implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String columnName;
	private String columnDescribe;
	private String colunmType;
	private String colunmLength;
	private String dateFormat;
	private Integer isEnalbe;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;
	private String colunmTypeName;
	private String dateFormatName;
	private String enalbeName;

	// Constructors

	/** default constructor */
	public TSysDsItemDict() {
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

	@Column(name = "COLUMN_NAME", unique = true, length = 80)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "COLUMN_DESCRIBE", unique = true, length = 120)
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

	@Column(name = "IS_ENALBE")
	public Integer getIsEnalbe() {
		return this.isEnalbe;
	}

	public void setIsEnalbe(Integer isEnalbe) {
		this.isEnalbe = isEnalbe;
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
	public String getColunmTypeName() {
		return colunmTypeName;
	}

	public void setColunmTypeName(String colunmTypeName) {
		this.colunmTypeName = colunmTypeName;
	}

	@Transient
	public String getDateFormatName() {
		return dateFormatName;
	}

	public void setDateFormatName(String dateFormatName) {
		this.dateFormatName = dateFormatName;
	}

	@Transient
	public String getEnalbeName() {
		return enalbeName;
	}

	public void setEnalbeName(String enalbeName) {
		this.enalbeName = enalbeName;
	}

}