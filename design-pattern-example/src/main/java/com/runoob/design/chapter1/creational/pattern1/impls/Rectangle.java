package com.runoob.design.chapter1.creational.pattern1.impls;

import com.runoob.design.chapter1.creational.pattern1.Shape;

/**
 * 矩形
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}

}
