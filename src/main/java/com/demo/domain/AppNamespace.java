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
@Table(name = "AppNamespace")
@SQLDelete(sql = "Update AppNamespace set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class AppNamespace extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "appnamespace_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "appnamespace_seq", sequenceName = "appnamespace_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "AppId", nullable = false)
	private String appId;
	@Column(name = "Format", nullable = false)
	private String format;
	@Column(name = "IsPublic")
	private boolean isPublic = false;
	@Column(name = "comment")
	private String comment;

	public String getAppId() {
		return appId;
	}

	public String getComment() {
		return comment;
	}

	public String getName() {
		return name;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean aPublic) {
		isPublic = aPublic;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
