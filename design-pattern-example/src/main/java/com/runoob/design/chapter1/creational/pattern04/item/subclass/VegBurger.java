package com.runoob.design.chapter1.creational.pattern04.item.subclass;

import com.runoob.design.chapter1.creational.pattern04.item.Burger;

/**
 * 素食汉堡
 */
public class VegBurger extends Burger {

	@Override
	public String name() {
		return "Veg Burger";
	}

	@Override
	public float price() {
		return 25.0f;
	}

}
