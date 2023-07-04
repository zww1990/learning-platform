package com.runoob.design.chapter4.javaee.pattern27;

/**
 * 创建客户端。
 */
public class Client {
	BusinessDelegate businessService;

	public Client(BusinessDelegate businessService) {
		this.businessService = businessService;
	}

	public void doTask() {
		businessService.doTask();
	}
}
