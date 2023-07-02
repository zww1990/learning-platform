package com.runoob.design.chapter3.behavioral.pattern18;

/**
 * 中介者模式（Mediator Pattern）
 */
public class MediatorPatternDemo {
	/**
	 * 使用 User 对象来显示他们之间的通信。
	 */
	public static void main(String[] args) {
		User robert = new User("Robert");
		User john = new User("John");

		robert.sendMessage("Hi! John!");
		john.sendMessage("Hello! Robert!");
	}
}
