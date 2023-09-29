package io.example.statemachine.constant;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
	/** 待支付 */
	WAIT_PAYMENT(1, "待支付"),
	/** 待发货 */
	WAIT_DELIVER(2, "待发货"),
	/** 待收货 */
	WAIT_RECEIVE(3, "待收货"),
	/** 已完成 */
	FINISH(4, "已完成");

	private Integer key;
	private String desc;

	public static OrderStatus getByKey(Integer key) {
		return Stream.of(values())//
				.filter(f -> f.getKey().equals(key))//
				.findFirst()//
				.orElseThrow(() -> new RuntimeException(String.format("此[%s]不存在！", key)));
	}
}
