package io.example.statemachine.service.impl;

import io.example.statemachine.constant.OrderStatus;
import io.example.statemachine.constant.OrderStatusChangeEvent;
import io.example.statemachine.domain.Order;
import io.example.statemachine.mapper.OrderMapper;
import io.example.statemachine.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * 订单服务接口实现类
 *
 * @author 张维维
 * @since 2023-09-29 18:28:40
 */
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    private final StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Object> stateMachineMemPersister;

    @Override
    public Order get(Long id) {
        return this.orderMapper.selectById(id);
    }

    @Override
    public Order create(Order order) {
        if (order == null) {
            order = new Order();
        }
        order.setStatus(OrderStatus.WAIT_PAYMENT.getCode());
        orderMapper.insert(order);
        return order;
    }

    @Override
    public Order pay(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名：{}, 尝试支付, 订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderStatusChangeEvent.PAYED, order)) {
            log.error("线程名：{}, 支付失败, 状态异常, 订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("支付失败, 订单状态异常");
        }
        return order;
    }

    @Override
    public Order deliver(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名：{}, 尝试发货, 订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderStatusChangeEvent.DELIVERY, order)) {
            log.error("线程名：{}, 发货失败, 状态异常, 订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("发货失败, 订单状态异常");
        }
        return order;
    }

    @Override
    public Order receive(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名：{}, 尝试收货, 订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderStatusChangeEvent.RECEIVED, order)) {
            log.error("线程名：{}, 收货失败, 状态异常, 订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("收货失败, 订单状态异常");
        }
        return order;
    }

    private synchronized boolean sendEvent(OrderStatusChangeEvent changeEvent, Order order) {
        // 发送订单状态转换事件
        boolean result = false;
        try {
            //启动状态机
            orderStateMachine.startReactively();
            //尝试恢复状态机状态
            stateMachineMemPersister.restore(orderStateMachine, order.getId());
            result = orderStateMachine.sendEvent(
                    MessageBuilder.withPayload(changeEvent)
                            .setHeader("order", order)
                            .build());
            //持久化状态机状态
            stateMachineMemPersister.persist(orderStateMachine, order.getId());
        } catch (Exception e) {
            log.error("订单操作失败", e);
        } finally {
            orderStateMachine.stopReactively();
        }
        return result;
    }
}
