package com.runoob.design.chapter3.behavioral.pattern15;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建命令调用类。
 */
public class Broker {
	private List<Order> orderList = new ArrayList<>();

	public void takeOrder(Order order) {
		orderList.add(order);
	}

	public void placeOrders() {
		this.orderList.forEach(Order::execute);
		orderList.clear();
	}
}
