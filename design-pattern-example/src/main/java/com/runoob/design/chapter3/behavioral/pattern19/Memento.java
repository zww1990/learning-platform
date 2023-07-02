package com.runoob.design.chapter3.behavioral.pattern19;

/**
 * 创建 Memento 类。
 */
public class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
