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

/**
 * 订单控制器
 *
 * @author 张维维
 * @since 2023-09-29 18:17:43
 */
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
    public Order create(@RequestBody Order order) {
        // 创建订单
        return orderService.create(order);
    }

    @PutMapping("/pay")
    public Order pay(@RequestParam Long id) {
        // 对订单进行支付
        return orderService.pay(id);
    }

    @PutMapping("/deliver")
    public Order deliver(@RequestParam Long id) {
        // 对订单进行确认发货
        return orderService.deliver(id);
    }

    @PutMapping("/receive")
    public Order receive(@RequestParam Long id) {
        // 对订单进行确认收货
        return orderService.receive(id);
    }
}
