package com.runoob.design.chapter2.structural.pattern13;

/**
 * 代理模式（Proxy Pattern）
 */
public class ProxyPatternDemo {
	/**
	 * 当被请求时，使用 ProxyImage 来获取 RealImage 类的对象。
	 */
	public static void main(String[] args) {
		Image image = new ProxyImage("test_10mb.jpg");

		// 图像将从磁盘加载
		image.display();
		System.out.println("......");
		// 图像将无法从磁盘加载
		image.display();
	}
}
