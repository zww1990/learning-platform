package com.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "NamespaceLock")
@Where(clause = "isDeleted = 0")
public class NamespaceLock extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "namespacelock_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "namespacelock_seq", sequenceName = "namespacelock_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "NamespaceId")
	private long namespaceId;

	public long getNamespaceId() {
		return namespaceId;
	}

	public void setNamespaceId(long namespaceId) {
		this.namespaceId = namespaceId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
