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

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "Release")
@SQLDelete(sql = "Update Release set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class Release extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "release_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "release_seq", sequenceName = "release_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "ReleaseKey", nullable = false)
	private String releaseKey;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "AppId", nullable = false)
	private String appId;
	@Column(name = "ClusterName", nullable = false)
	private String clusterName;
	@Column(name = "NamespaceName", nullable = false)
	private String namespaceName;
	@Column(name = "Configurations", nullable = false)
	@Lob
	private String configurations;
	@Column(name = "comment", nullable = false)
	private String comment;
	@Column(name = "IsAbandoned")
	private boolean isAbandoned;

	public String getReleaseKey() {
		return releaseKey;
	}

	public String getAppId() {
		return appId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public String getComment() {
		return comment;
	}

	public String getConfigurations() {
		return configurations;
	}

	public String getNamespaceName() {
		return namespaceName;
	}

	public String getName() {
		return name;
	}

	public void setReleaseKey(String releaseKey) {
		this.releaseKey = releaseKey;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setConfigurations(String configurations) {
		this.configurations = configurations;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAbandoned() {
		return isAbandoned;
	}

	public void setAbandoned(boolean abandoned) {
		isAbandoned = abandoned;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
