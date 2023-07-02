package com.runoob.design.chapter3.behavioral.pattern22.customer;

import com.runoob.design.chapter3.behavioral.pattern22.AbstractCustomer;

public class RealCustomer extends AbstractCustomer {
	public RealCustomer(String name) {
		this.name = name;
	}

	@Override
	public boolean isNil() {
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
