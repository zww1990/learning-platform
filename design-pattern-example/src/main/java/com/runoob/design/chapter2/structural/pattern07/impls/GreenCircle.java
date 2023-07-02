package com.runoob.design.chapter2.structural.pattern07.impls;

import com.runoob.design.chapter2.structural.pattern07.DrawAPI;

/**
 * 绿圈
 */
public class GreenCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: green, radius: " + radius
				+ ", x: " + x + ", " + y + "]");
	}

}
