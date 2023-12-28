package io.online.videosite.domain;

import jakarta.persistence.*;
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
@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 创建人昵称
     */
    @Transient
    private String creatorNick;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedDate;
    /**
     * 修改人
     */
    private String modifier;
}
