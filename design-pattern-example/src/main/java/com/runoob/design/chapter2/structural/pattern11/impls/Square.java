package com.runoob.design.chapter2.structural.pattern11.impls;

import com.runoob.design.chapter2.structural.pattern11.Shape;

/**
 * 正方形
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Square::draw()");
	}

}
