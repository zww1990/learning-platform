package com.runoob.design.chapter1.creational.pattern4.packing;

import com.runoob.design.chapter1.creational.pattern4.Packing;

/**
 * 纸盒
 */
public class Wrapper implements Packing {

	@Override
	public String pack() {
		return "Wrapper";
	}

}
