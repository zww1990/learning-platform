package com.runoob.design.chapter1.creational.pattern02.shape.impls;

import com.runoob.design.chapter1.creational.pattern02.shape.Shape;

/**
 * 矩形
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}

}
