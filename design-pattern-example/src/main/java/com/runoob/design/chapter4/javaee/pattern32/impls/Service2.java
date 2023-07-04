package com.runoob.design.chapter4.javaee.pattern32.impls;

import com.runoob.design.chapter4.javaee.pattern32.Service;

/**
 * 服务2实现
 */
public class Service2 implements Service {

	@Override
	public String getName() {
		return "Service2";
	}

	@Override
	public void execute() {
		System.out.println("Executing Service2");
	}

}
