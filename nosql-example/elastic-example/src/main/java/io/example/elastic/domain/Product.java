package io.example.elastic.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 产品
 * @author zww
 * @since 2023-08-08 19:32:19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Product {
    /**
     * 产品编码
     */
    private String sku;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品价格
     */
    private Double price;
}
