package com.runoob.design.chapter1.creational.pattern4.item.subclass;

import com.runoob.design.chapter1.creational.pattern4.item.Burger;

/**
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger {

	@Override
	public String name() {
		return "Chicken Burger";
	}

	@Override
	public float price() {
		return 50.5f;
	}

}
