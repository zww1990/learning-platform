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
 * TSysDsConfigFile entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sys_ds_config_file")
public class TSysDsConfigFile implements java.io.Serializable {

	// Fields

	private Integer rowId;
	private String dataSourceId;
	private String fileReceiveMethod;
	private String filePath;
	private String fileNameStandard;
	private String fileType;
	private String dataItemSplit;
	private Integer ignoreStartLines;
	private Integer ignoreEndLines;
	private String ftpHost;
	private Integer ftpPort;
	private String ftpUser;
	private String ftpPassword;
	private String ftpPath;
	private Integer isDelete;
	private Date deleteTime;
	private Date createTime;
	private Date updateTime;
	private String creater;
	private String updater;

	// Constructors

	/** default constructor */
	public TSysDsConfigFile() {
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

	@Column(name = "FILE_RECEIVE_METHOD", length = 10)
	public String getFileReceiveMethod() {
		return this.fileReceiveMethod;
	}

	public void setFileReceiveMethod(String fileReceiveMethod) {
		this.fileReceiveMethod = fileReceiveMethod;
	}

	@Column(name = "FILE_PATH", length = 120)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_NAME_STANDARD", length = 50)
	public String getFileNameStandard() {
		return this.fileNameStandard;
	}

	public void setFileNameStandard(String fileNameStandard) {
		this.fileNameStandard = fileNameStandard;
	}

	@Column(name = "FILE_TYPE", length = 50)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "DATA_ITEM_SPLIT", length = 10)
	public String getDataItemSplit() {
		return this.dataItemSplit;
	}

	public void setDataItemSplit(String dataItemSplit) {
		this.dataItemSplit = dataItemSplit;
	}

	@Column(name = "IGNORE_START_LINES")
	public Integer getIgnoreStartLines() {
		return this.ignoreStartLines;
	}

	public void setIgnoreStartLines(Integer ignoreStartLines) {
		this.ignoreStartLines = ignoreStartLines;
	}

	@Column(name = "IGNORE_END_LINES")
	public Integer getIgnoreEndLines() {
		return this.ignoreEndLines;
	}

	public void setIgnoreEndLines(Integer ignoreEndLines) {
		this.ignoreEndLines = ignoreEndLines;
	}

	@Column(name = "FTP_HOST", length = 50)
	public String getFtpHost() {
		return this.ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	@Column(name = "FTP_PORT")
	public Integer getFtpPort() {
		return this.ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	@Column(name = "FTP_USER", length = 50)
	public String getFtpUser() {
		return this.ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	@Column(name = "FTP_PASSWORD", length = 50)
	public String getFtpPassword() {
		return this.ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	@Column(name = "FTP_PATH", length = 50)
	public String getFtpPath() {
		return this.ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
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

}