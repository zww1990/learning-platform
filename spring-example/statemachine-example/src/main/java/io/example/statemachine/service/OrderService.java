package io.example.statemachine.service;

import io.example.statemachine.domain.Order;

public interface OrderService {

	Order get(Long id);

	void create(Order order);

	void pay(Long id);

	void deliver(Long id);

	void receive(Long id);

}
