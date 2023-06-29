package com.example.shardingtablesplitting.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shardingtablesplitting.domain.Order;

import jakarta.annotation.Resource;

@SpringBootTest
public class OrderRepositoryTests {
	@Resource
	private OrderRepository orderDao;

	@Test
	public void testInsertOrder() {
		try {
			int rows = IntStream.range(0, 10).map(m -> {
				Order order = new Order();
				order.setUserId(100 + m);
				order.setProductName("空调" + m);
				order.setQuantity(10);
				return this.orderDao.insertOrder(order);
			}).sum();
			System.err.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectOrders() {
		try {
			List<Order> orders = this.orderDao.selectOrders();
			System.err.println(orders.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectOrdersByUserIds() {
		try {
			List<Order> orders = this.orderDao.selectOrdersByUserIds(Arrays.asList(104, 105));
			System.err.println(orders.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
