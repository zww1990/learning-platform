package com.runoob.design.chapter1.creational.pattern02.color.impls;

import com.runoob.design.chapter1.creational.pattern02.color.Color;

/**
 * 红色
 */
public class Red implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Red::fill() method.");
	}

}
