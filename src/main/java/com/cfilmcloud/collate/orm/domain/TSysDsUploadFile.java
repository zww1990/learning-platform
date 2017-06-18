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
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TSysDsUploadFile entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_ds_upload_file")
public class TSysDsUploadFile implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String dataSourceId;
	private String fileName;
	private String fileNameOld;
	private Long fileSize;
	private String filePath;
	private String fileType;
	private Date checkDateBegin;
	private Date checkDateEnd;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;
	private String dataSourceName;

	// Constructors

	/** default constructor */
	public TSysDsUploadFile() {
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

	@Column(name = "FILE_NAME", length = 200)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_NAME_OLD", length = 200)
	public String getFileNameOld() {
		return this.fileNameOld;
	}

	public void setFileNameOld(String fileNameOld) {
		this.fileNameOld = fileNameOld;
	}

	@Column(name = "FILE_SIZE")
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_PATH", length = 200)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_TYPE", length = 100)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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

	@Column(name = "IS_DELETE", nullable = false)
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 19)
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

}