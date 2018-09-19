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
@Table(name = "audit")
@SQLDelete(sql = "Update \"audit\" set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class Audit extends BaseEntity {
	public enum OP {
		INSERT, UPDATE, DELETE
	}

	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "audit_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "audit_seq", sequenceName = "audit_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "EntityName", nullable = false)
	private String entityName;
	@Column(name = "EntityId")
	private Long entityId;
	@Column(name = "OpName", nullable = false)
	private String opName;
	@Column(name = "comment")
	private String comment;

	public String getComment() {
		return comment;
	}

	public Long getEntityId() {
		return entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getOpName() {
		return opName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
