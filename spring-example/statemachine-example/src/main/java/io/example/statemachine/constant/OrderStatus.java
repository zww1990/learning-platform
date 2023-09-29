package io.example.statemachine.constant;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author 张维维
 * @since 2023-09-29 18:21:45
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    /**
     * 待支付
     */
    WAIT_PAYMENT(1, "待支付"),
    /**
     * 待发货
     */
    WAIT_DELIVER(2, "待发货"),
    /**
     * 待收货
     */
    WAIT_RECEIVE(3, "待收货"),
    /**
     * 已完成
     */
    FINISH(4, "已完成");

    private final Integer code;
    private final String name;

    /**
     * 根据状态编码查找对应的枚举类
     *
     * @param code 状态编码
     * @return {@link OrderStatus}
     */
    public static OrderStatus valueOfCode(Integer code) {
        return Stream.of(values())//
                .filter(f -> f.getCode().equals(code))//
                .findFirst()//
                .orElseThrow(() -> new RuntimeException(String.format("此[%s]不存在！", code)));
    }
}
