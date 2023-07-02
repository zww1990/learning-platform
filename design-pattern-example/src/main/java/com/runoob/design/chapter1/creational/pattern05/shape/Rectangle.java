package com.runoob.design.chapter1.creational.pattern05.shape;

import com.runoob.design.chapter1.creational.pattern05.Shape;

/**
 * 矩形
 */
public class Rectangle extends Shape {
	public Rectangle() {
		type = "Rectangle";
	}

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}

}
