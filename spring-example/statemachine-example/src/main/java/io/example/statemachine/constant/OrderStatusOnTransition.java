package io.example.statemachine.constant;

import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 订单状态转换
 *
 * @author 张维维
 * @since 2023-09-29 19:47:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface OrderStatusOnTransition {
    /**
     * 源状态
     */
    OrderStatus[] source() default {};

    /**
     * 目标状态
     */
    OrderStatus[] target() default {};
}
