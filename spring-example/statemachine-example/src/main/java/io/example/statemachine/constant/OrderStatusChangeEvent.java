package io.example.statemachine.constant;

public enum OrderStatusChangeEvent {
	/** 支付 */
	PAYED,
	/** 发货 */
	DELIVERY,
	/** 确认收货 */
	RECEIVED;
}
