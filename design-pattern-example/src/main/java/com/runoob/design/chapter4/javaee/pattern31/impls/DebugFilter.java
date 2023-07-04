package com.runoob.design.chapter4.javaee.pattern31.impls;

import com.runoob.design.chapter4.javaee.pattern31.Filter;

/**
 * 调试过滤器
 */
public class DebugFilter implements Filter {

	@Override
	public void execute(String request) {
		System.out.println("request log: " + request);
	}

}
