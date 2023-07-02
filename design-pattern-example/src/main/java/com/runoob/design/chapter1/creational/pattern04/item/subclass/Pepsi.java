package com.runoob.design.chapter1.creational.pattern04.item.subclass;

import com.runoob.design.chapter1.creational.pattern04.item.ColdDrink;

/**
 * 百事可乐
 */
public class Pepsi extends ColdDrink {

	@Override
	public String name() {
		return "Pepsi";
	}

	@Override
	public float price() {
		return 35.0f;
	}

}
