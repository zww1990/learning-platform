package com.runoob.design.chapter4.javaee.pattern31;

import com.runoob.design.chapter4.javaee.pattern31.impls.AuthenticationFilter;
import com.runoob.design.chapter4.javaee.pattern31.impls.DebugFilter;

/**
 * 拦截过滤器模式（Intercepting Filter Pattern）
 */
public class FrontControllerPatternDemo {
	/**
	 * 使用 Client 来演示拦截过滤器设计模式。
	 */
	public static void main(String[] args) {
		FilterManager filterManager = new FilterManager(new Target());
		filterManager.setFilter(new AuthenticationFilter());
		filterManager.setFilter(new DebugFilter());

		Client client = new Client();
		client.setFilterManager(filterManager);
		client.sendRequest("HOME");
	}
}
