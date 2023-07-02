package com.runoob.design.chapter3.behavioral.pattern20;

/**
 * 创建 Observer 类。
 */
public abstract class Observer {
	protected Subject subject;

	public abstract void update();
}
