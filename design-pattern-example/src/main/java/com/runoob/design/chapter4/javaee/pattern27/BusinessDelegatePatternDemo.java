package com.runoob.design.chapter4.javaee.pattern27;

/**
 * 业务代表模式（Business Delegate Pattern）
 */
public class BusinessDelegatePatternDemo {
	/**
	 * 使用 BusinessDelegate 和 Client 类来演示业务代表模式。
	 */
	public static void main(String[] args) {

		BusinessDelegate businessDelegate = new BusinessDelegate();
		businessDelegate.setServiceType("EJB");

		Client client = new Client(businessDelegate);
		client.doTask();

		businessDelegate.setServiceType("JMS");
		client.doTask();
	}
}
