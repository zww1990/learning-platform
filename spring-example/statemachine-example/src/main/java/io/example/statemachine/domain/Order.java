package io.example.statemachine.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Order {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 价格
     */
    private Double price;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private String updateUser;
}
