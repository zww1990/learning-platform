package com.runoob.design.javaee.interceptingfilter;

public class AuthenticationFilter implements Filter {

	@Override
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}

}
