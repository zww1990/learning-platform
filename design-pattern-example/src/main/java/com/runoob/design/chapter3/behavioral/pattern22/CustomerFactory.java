package com.runoob.design.chapter3.behavioral.pattern22;

import com.runoob.design.chapter3.behavioral.pattern22.customer.NullCustomer;
import com.runoob.design.chapter3.behavioral.pattern22.customer.RealCustomer;

/**
 * 创建 CustomerFactory 类。
 */
public class CustomerFactory {
	public static final String[] names = { "Rob", "Joe", "Julie" };

	public static AbstractCustomer getCustomer(String name) {
		for (String s : names) {
			if (s.equalsIgnoreCase(name)) {
				return new RealCustomer(name);
			}
		}
		return new NullCustomer();
	}
}
