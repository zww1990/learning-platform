package com.runoob.design.chapter4.javaee.pattern30;

import com.runoob.design.chapter4.javaee.pattern30.views.HomeView;
import com.runoob.design.chapter4.javaee.pattern30.views.StudentView;

/**
 * 调度器
 */
public class Dispatcher {
	private StudentView studentView;
	private HomeView homeView;

	public Dispatcher() {
		studentView = new StudentView();
		homeView = new HomeView();
	}

	public void dispatch(String request) {
		if (request.equalsIgnoreCase("STUDENT")) {
			studentView.show();
		} else {
			homeView.show();
		}
	}
}
