package com.runoob.design.chapter1.creational.pattern04.item;

import com.runoob.design.chapter1.creational.pattern04.Item;
import com.runoob.design.chapter1.creational.pattern04.Packing;
import com.runoob.design.chapter1.creational.pattern04.packing.Wrapper;

/**
 * 汉堡
 */
public abstract class Burger implements Item {

	@Override
	public abstract String name();

	@Override
	public Packing packing() {
		return new Wrapper();
	}

	@Override
	public abstract float price();

}
