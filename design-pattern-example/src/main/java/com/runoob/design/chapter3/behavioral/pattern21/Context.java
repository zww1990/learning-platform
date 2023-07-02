package com.runoob.design.chapter3.behavioral.pattern21;

/**
 * 创建 Context 类。
 */
public class Context {
	private State state;

	public Context() {
		state = null;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}
}
