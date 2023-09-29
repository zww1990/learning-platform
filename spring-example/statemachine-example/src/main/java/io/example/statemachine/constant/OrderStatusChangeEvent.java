package io.example.statemachine.constant;

/**
 * 订单状态更改事件
 *
 * @author 张维维
 * @since 2023-09-29 18:26:49
 */
public enum OrderStatusChangeEvent {
    /**
     * 支付
     */
    PAYED,
    /**
     * 发货
     */
    DELIVERY,
    /**
     * 确认收货
     */
    RECEIVED,
}
