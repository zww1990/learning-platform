package com.runoob.design.chapter1.creational.factory.impls;

import com.runoob.design.chapter1.creational.factory.Shape;

/**
 * 圆形
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}

}
