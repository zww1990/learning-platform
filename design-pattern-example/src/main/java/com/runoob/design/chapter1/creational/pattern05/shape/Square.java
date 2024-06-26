package com.runoob.design.chapter1.creational.pattern05.shape;

import com.runoob.design.chapter1.creational.pattern05.Shape;

/**
 * 正方形
 */
public class Square extends Shape {
	public Square() {
		type = "Square";
	}

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}

}
