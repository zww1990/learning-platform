package com.runoob.design.chapter3.behavioral.nullobject;

public class NullCustomer extends AbstractCustomer {

	@Override
	public boolean isNil() {
		return true;
	}

	@Override
	public String getName() {
		return "Not Available in Customer Database";
	}

}
