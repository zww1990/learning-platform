package com.runoob.design.chapter1.creational.pattern4.packing;

import com.runoob.design.chapter1.creational.pattern4.Packing;

/**
 * 瓶子
 */
public class Bottle implements Packing {

	@Override
	public String pack() {
		return "Bottle";
	}

}
