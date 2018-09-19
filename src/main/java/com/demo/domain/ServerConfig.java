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
@Table(name = "ServerConfig")
@SQLDelete(sql = "Update ServerConfig set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class ServerConfig extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "serverconfig_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "serverconfig_seq", sequenceName = "serverconfig_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "Key", nullable = false)
	private String key;
	@Column(name = "cluster", nullable = false)
	private String cluster;
	@Column(name = "VALUE", nullable = false)
	private String value;
	@Column(name = "comment", nullable = false)
	private String comment;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
