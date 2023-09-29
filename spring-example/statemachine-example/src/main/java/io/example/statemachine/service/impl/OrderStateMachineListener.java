package io.example.statemachine.service.impl;

import io.example.statemachine.constant.OrderStatus;
import io.example.statemachine.constant.OrderStatusChangeEvent;
import io.example.statemachine.constant.OrderStatusOnTransition;
import io.example.statemachine.domain.Order;
import io.example.statemachine.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component
@WithStateMachine(name = "orderStateMachine")
@Slf4j
@AllArgsConstructor
public class OrderStateMachineListener {
    private final OrderMapper orderMapper;

    @OrderStatusOnTransition(source = OrderStatus.WAIT_PAYMENT, target = OrderStatus.WAIT_DELIVER)
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order == null) {
            throw new RuntimeException("订单不存在！");
        }
        log.info("确认支付，状态机反馈信息：{}", message.getHeaders());
        //更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getCode());
        orderMapper.updateById(order);
    }

    @OrderStatusOnTransition(source = OrderStatus.WAIT_DELIVER, target = OrderStatus.WAIT_RECEIVE)
    public void deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order == null) {
            throw new RuntimeException("订单不存在！");
        }
        log.info("确认发货，状态机反馈信息：{}", message.getHeaders());
        //更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getCode());
        orderMapper.updateById(order);
    }

    @OrderStatusOnTransition(source = OrderStatus.WAIT_RECEIVE, target = OrderStatus.FINISH)
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        if (order == null) {
            throw new RuntimeException("订单不存在！");
        }
        log.info("确认收货，状态机反馈信息：{}", message.getHeaders());
        //更新订单
        order.setStatus(OrderStatus.FINISH.getCode());
        orderMapper.updateById(order);
    }
}
