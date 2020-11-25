package com.runoob.design.intercepting;

public class AuthenticationFilter implements Filter {

	@Override
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}

}
