package com.runoob.design.chapter1.creational.pattern04.item.subclass;

import com.runoob.design.chapter1.creational.pattern04.item.ColdDrink;

/**
 * 可口可乐
 */
public class Coke extends ColdDrink {

	@Override
	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return 30.0f;
	}

}
