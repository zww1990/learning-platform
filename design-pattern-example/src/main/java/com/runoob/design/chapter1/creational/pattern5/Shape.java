package com.runoob.design.chapter1.creational.pattern5;

/**
 * 形状
 */
public abstract class Shape implements Cloneable {
	private String id;
	protected String type;

	public abstract void draw();

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Shape clone() {
		try {
			return (Shape) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
