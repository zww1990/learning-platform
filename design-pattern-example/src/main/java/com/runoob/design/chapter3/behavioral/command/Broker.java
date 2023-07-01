package com.runoob.design.chapter3.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class Broker {
	private List<Order> orderList = new ArrayList<Order>();

	public void takeOrder(Order order) {
		orderList.add(order);
	}

	public void placeOrders() {
		this.orderList.stream().forEach(order -> {
			order.execute();
		});
		orderList.clear();
	}
}
