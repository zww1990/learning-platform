package com.runoob.design.chapter1.creational.pattern02.color.impls;

import com.runoob.design.chapter1.creational.pattern02.color.Color;

/**
 * 绿色
 */
public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}

}
