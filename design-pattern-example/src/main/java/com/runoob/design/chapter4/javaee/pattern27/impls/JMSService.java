package com.runoob.design.chapter4.javaee.pattern27.impls;

import com.runoob.design.chapter4.javaee.pattern27.BusinessService;

/**
 * JMS服务
 */
public class JMSService implements BusinessService {

	@Override
	public void doProcessing() {
		System.out.println("Processing task by invoking JMS Service");
	}

}
