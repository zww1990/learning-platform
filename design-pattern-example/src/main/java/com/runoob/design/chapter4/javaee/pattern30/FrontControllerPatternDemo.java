package com.runoob.design.chapter4.javaee.pattern30;

/**
 * 前端控制器模式（Front Controller Pattern）
 */
public class FrontControllerPatternDemo {
	/**
	 * 使用 FrontController 来演示前端控制器设计模式。
	 */
	public static void main(String[] args) {
		FrontController frontController = new FrontController();
		frontController.dispatchRequest("HOME");
		frontController.dispatchRequest("STUDENT");
	}
}
