package com.runoob.design.chapter2.structural.pattern10.impls;

import com.runoob.design.chapter2.structural.pattern10.Shape;

/**
 * 矩形
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}

}
