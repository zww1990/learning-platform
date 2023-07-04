package com.runoob.design.chapter4.javaee.pattern32;

/**
 * 服务定位器模式（Service Locator Pattern）
 */
public class ServiceLocatorPatternDemo {
	/**
	 * 使用 ServiceLocator 来演示服务定位器设计模式。
	 */
	public static void main(String[] args) {
		Service service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
		service = ServiceLocator.getService("Service1");
		service.execute();
		service = ServiceLocator.getService("Service2");
		service.execute();
	}
}
