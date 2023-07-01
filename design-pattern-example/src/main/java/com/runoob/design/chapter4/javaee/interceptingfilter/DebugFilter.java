package com.runoob.design.chapter4.javaee.interceptingfilter;

public class DebugFilter implements Filter {

	@Override
	public void execute(String request) {
		System.out.println("request log: " + request);
	}

}
