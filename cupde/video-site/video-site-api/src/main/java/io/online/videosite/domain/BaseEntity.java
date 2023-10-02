package io.online.videosite.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 抽象实体类
 *
 * @author 张维维
 * @since 2023-10-02 13:04:53
 */
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    private LocalDateTime createdDate;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedDate;
    /**
     * 修改人
     */
    private String modifier;
}
