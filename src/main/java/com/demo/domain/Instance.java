package com.demo.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "Instance")
public class Instance {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "instance_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "instance_seq", sequenceName = "instance_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "AppId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "DataCenter", nullable = false)
	private String dataCenter;
	@Column(name = "Ip", nullable = false)
	private String ip;
	@Column(name = "DataChange_CreatedTime", nullable = false)
	private Date dataChangeCreatedTime;
	@Column(name = "DataChange_LastTime")
	private Date dataChangeLastModifiedTime;

	@PrePersist
	protected void prePersist() {
		if (this.dataChangeCreatedTime == null) {
			dataChangeCreatedTime = new Date();
		}
		if (this.dataChangeLastModifiedTime == null) {
			dataChangeLastModifiedTime = dataChangeCreatedTime;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getDataCenter() {
		return dataCenter;
	}

	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDataChangeCreatedTime() {
		return dataChangeCreatedTime;
	}

	public void setDataChangeCreatedTime(Date dataChangeCreatedTime) {
		this.dataChangeCreatedTime = dataChangeCreatedTime;
	}

	public Date getDataChangeLastModifiedTime() {
		return dataChangeLastModifiedTime;
	}

	public void setDataChangeLastModifiedTime(Date dataChangeLastModifiedTime) {
		this.dataChangeLastModifiedTime = dataChangeLastModifiedTime;
	}
}
