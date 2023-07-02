package com.runoob.design.chapter3.behavioral.pattern22.customer;

import com.runoob.design.chapter3.behavioral.pattern22.AbstractCustomer;

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
