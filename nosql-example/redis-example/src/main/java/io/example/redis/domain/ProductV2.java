package io.example.redis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

/**
 * 产品
 *
 * @author zww
 * @since 2023-08-08 19:32:19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@RedisHash(value = "products")
public class ProductV2 {
    /**
     * 主键，自动生成
     */
    @Id
    private String id;
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
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdDate;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
