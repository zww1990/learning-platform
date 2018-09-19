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
@Table(name = "Item")
@SQLDelete(sql = "Update Item set isDeleted = 1 where id = ?")
@Where(clause = "isDeleted = 0")
public class Item extends BaseEntity {
	@Id
	@Column(name = "Id")
	@GeneratedValue(generator = "item_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1, initialValue = 1)
	private long id;
	@Column(name = "NamespaceId", nullable = false)
	private long namespaceId;
	@Column(name = "key", nullable = false)
	private String key;
	@Column(name = "VALUE")
	@Lob
	private String value;
	@Column(name = "comment")
	private String comment;
	@Column(name = "LineNum")
	private Integer lineNum;

	public String getComment() {
		return comment;
	}

	public String getKey() {
		return key;
	}

	public long getNamespaceId() {
		return namespaceId;
	}

	public String getValue() {
		return value;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setNamespaceId(long namespaceId) {
		this.namespaceId = namespaceId;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
