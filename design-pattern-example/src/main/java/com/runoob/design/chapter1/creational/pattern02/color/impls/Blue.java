package com.runoob.design.chapter1.creational.pattern02.color.impls;

import com.runoob.design.chapter1.creational.pattern02.color.Color;

/**
 * 蓝色
 */
public class Blue implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Blue::fill() method.");
	}

}
