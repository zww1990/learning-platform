package com.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "COMMIT")
@SQLDelete(sql = "Update Commit set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class Commit extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "commit_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "commit_seq", sequenceName = "commit_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Lob
	@Column(name = "ChangeSets", nullable = false)
	private String changeSets;
	@Column(name = "AppId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "NamespaceName", nullable = false)
	private String namespaceName;
	@Column(name = "comment")
	private String comment;

	public String getChangeSets() {
		return changeSets;
	}

	public void setChangeSets(String changeSets) {
		this.changeSets = changeSets;
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

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
