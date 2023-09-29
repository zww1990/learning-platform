package io.example.statemachine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.example.statemachine.domain.Order;
import io.example.statemachine.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/order")
@AllArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@GetMapping("/get")
	public Order get(@RequestParam Long id) {
		// 根据id查询订单
		return orderService.get(id);
	}

	@PostMapping("/create")
	public String create(@RequestBody Order order) {
		// 创建订单
		orderService.create(order);
		return "success";
	}

	@PutMapping("/pay")
	public String pay(@RequestParam Long id) {
		// 对订单进行支付
		orderService.pay(id);
		return "success";
	}

	@PutMapping("/deliver")
	public String deliver(@RequestParam Long id) {
		// 对订单进行确认发货
		orderService.deliver(id);
		return "success";
	}

	@PutMapping("/receive")
	public String receive(@RequestParam Long id) {
		// 对订单进行确认收货
		orderService.receive(id);
		return "success";
	}
}
