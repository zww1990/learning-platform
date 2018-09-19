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

@Entity
@Table(name = "GrayReleaseRule")
@SQLDelete(sql = "Update GrayReleaseRule set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class GrayReleaseRule extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "grayreleaserule_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "grayreleaserule_seq", sequenceName = "grayreleaserule_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "appId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "NamespaceName", nullable = false)
	private String namespaceName;
	@Column(name = "BranchName", nullable = false)
	private String branchName;
	@Column(name = "Rules")
	private String rules;
	@Column(name = "releaseId", nullable = false)
	private Long releaseId;
	@Column(name = "BranchStatus", nullable = false)
	private int branchStatus;

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

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Long getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Long releaseId) {
		this.releaseId = releaseId;
	}

	public int getBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(int branchStatus) {
		this.branchStatus = branchStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
