package com.example.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 账户表 实体类。
 *
 * @author zww19
 * @since 2023-07-28
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(value = "tb_account")
public class Account {
    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 生日
     */
    private LocalDateTime birthday;

}
