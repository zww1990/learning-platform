package com.runoob.design.chapter4.javaee.pattern32.impls;

import com.runoob.design.chapter4.javaee.pattern32.Service;

/**
 * 服务1实现
 */
public class Service1 implements Service {

	@Override
	public String getName() {
		return "Service1";
	}

	@Override
	public void execute() {
		System.out.println("Executing Service1");
	}

}
