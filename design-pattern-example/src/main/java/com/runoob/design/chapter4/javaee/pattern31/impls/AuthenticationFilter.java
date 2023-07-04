package com.runoob.design.chapter4.javaee.pattern31.impls;

import com.runoob.design.chapter4.javaee.pattern31.Filter;

/**
 * 身份认证过滤器
 */
public class AuthenticationFilter implements Filter {

	@Override
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}

}
