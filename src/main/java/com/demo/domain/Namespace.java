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
@Table(name = "Namespace")
@SQLDelete(sql = "Update Namespace set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class Namespace extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "namespace_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "namespace_seq", sequenceName = "namespace_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "appId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "NamespaceName", nullable = false)
	private String namespaceName;

	public Namespace() {
	}

	public Namespace(String appId, String clusterName, String namespaceName) {
		this.appId = appId;
		this.clusterName = clusterName;
		this.namespaceName = namespaceName;
	}

	public String getAppId() {
		return appId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
