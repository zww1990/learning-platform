package com.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "ReleaseHistory")
@SQLDelete(sql = "Update ReleaseHistory set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class ReleaseHistory extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "releasehistory_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "releasehistory_seq", sequenceName = "releasehistory_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "AppId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "NamespaceName", nullable = false)
	private String namespaceName;
	@Column(name = "BranchName", nullable = false)
	private String branchName;
	@Column(name = "ReleaseId")
	private long releaseId;
	@Column(name = "PreviousReleaseId")
	private long previousReleaseId;
	@Column(name = "Operation")
	private int operation;
	@Column(name = "OperationContext", nullable = false)
	private String operationContext;

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

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(long releaseId) {
		this.releaseId = releaseId;
	}

	public long getPreviousReleaseId() {
		return previousReleaseId;
	}

	public void setPreviousReleaseId(long previousReleaseId) {
		this.previousReleaseId = previousReleaseId;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getOperationContext() {
		return operationContext;
	}

	public void setOperationContext(String operationContext) {
		this.operationContext = operationContext;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
