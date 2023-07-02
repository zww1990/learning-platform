package com.runoob.design.chapter2.structural.pattern7.impls;

import com.runoob.design.chapter2.structural.pattern7.DrawAPI;

/**
 * 红圈
 */
public class RedCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: red, radius: " + radius
				+ ", x: " + x + ", " + y + "]");
	}

}
