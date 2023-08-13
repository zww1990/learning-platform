package io.example.elastic.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "products")
public class ProductV3 {
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
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime createdDate;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime lastModifiedDate;
}
