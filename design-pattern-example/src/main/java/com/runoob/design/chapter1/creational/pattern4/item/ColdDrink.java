package com.runoob.design.chapter1.creational.pattern4.item;

import com.runoob.design.chapter1.creational.pattern4.packing.Bottle;
import com.runoob.design.chapter1.creational.pattern4.Item;
import com.runoob.design.chapter1.creational.pattern4.Packing;

/**
 * 冷饮
 */
public abstract class ColdDrink implements Item {

	@Override
	public abstract String name();

	@Override
	public Packing packing() {
		return new Bottle();
	}

	@Override
	public abstract float price();

}
