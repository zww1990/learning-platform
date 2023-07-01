package com.runoob.design.chapter1.creational.factory.impls;

import com.runoob.design.chapter1.creational.factory.Shape;

/**
 * 正方形
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}

}
