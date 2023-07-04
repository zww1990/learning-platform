package com.runoob.design.chapter4.javaee.pattern28;

/**
 * 组合实体模式（Composite Entity Pattern）
 */
public class CompositeEntityPatternDemo {
	/**
	 * 使用 Client 来演示组合实体设计模式的用法。
	 */
	public static void main(String[] args) {
		Client client = new Client();
		client.setData("Test", "Data");
		client.printData();
		client.setData("Second Test", "Data1");
		client.printData();
	}
}
