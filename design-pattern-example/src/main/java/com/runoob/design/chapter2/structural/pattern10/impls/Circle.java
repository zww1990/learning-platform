package com.runoob.design.chapter2.structural.pattern10.impls;

import com.runoob.design.chapter2.structural.pattern10.Shape;

/**
 * 圆形
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Circle");
	}

}
