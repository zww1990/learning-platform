package io.example.statemachine.service;

import io.example.statemachine.domain.Order;

/**
 * 订单服务接口
 *
 * @author 张维维
 * @since 2023-09-29 18:28:20
 */
public interface OrderService {

    /**
     * 根据id查询订单
     *
     * @param id 主键
     * @return {@link Order}
     */
    Order get(Long id);

    /**
     * 创建订单
     *
     * @param order {@link Order}
     * @return {@link Order}
     */
    Order create(Order order);

    /**
     * 对订单进行支付
     *
     * @param id 主键
     * @return {@link Order}
     */
    Order pay(Long id);

    /**
     * 对订单进行确认发货
     *
     * @param id 主键
     * @return {@link Order}
     */
    Order deliver(Long id);

    /**
     * 对订单进行确认收货
     *
     * @param id 主键
     * @return {@link Order}
     */
    Order receive(Long id);

}
