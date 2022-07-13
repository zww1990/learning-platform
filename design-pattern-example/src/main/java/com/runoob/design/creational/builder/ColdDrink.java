package com.runoob.design.creational.builder;

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
