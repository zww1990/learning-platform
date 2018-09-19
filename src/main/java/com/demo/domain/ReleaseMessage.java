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
@Table(name = "ReleaseMessage")
public class ReleaseMessage {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "releasemessage_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "releasemessage_seq", sequenceName = "releasemessage_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "Message", nullable = false)
	private String message;
	@Column(name = "DataChange_LastTime")
	private Date dataChangeLastModifiedTime;

	@PrePersist
	protected void prePersist() {
		if (this.dataChangeLastModifiedTime == null) {
			dataChangeLastModifiedTime = new Date();
		}
	}

	public ReleaseMessage() {
	}

	public ReleaseMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
