package com.runoob.design.chapter4.javaee.interceptingfilter;

public class Target {
	public void execute(String request) {
		System.out.println("Executing request: " + request);
	}
}
