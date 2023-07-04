package com.runoob.design.chapter4.javaee.pattern27.impls;

import com.runoob.design.chapter4.javaee.pattern27.BusinessService;

/**
 * EJB服务
 */
public class EJBService implements BusinessService {

	@Override
	public void doProcessing() {
		System.out.println("Processing task by invoking EJB Service");
	}

}
