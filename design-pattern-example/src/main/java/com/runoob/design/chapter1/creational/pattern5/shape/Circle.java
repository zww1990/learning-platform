package com.runoob.design.chapter1.creational.pattern5.shape;

import com.runoob.design.chapter1.creational.pattern5.Shape;

/**
 * 圆形
 */
public class Circle extends Shape {
	public Circle() {
		type = "Circle";
	}

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}

}
