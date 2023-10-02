package io.online.videosite.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 视频类别实体类
 *
 * @author 张维维
 * @since 2023-10-02 13:20:07
 */
@Table(name = "t_category")
@Entity
@Getter
@Setter
@ToString
public class Category extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 类别名称
     */
    private String categoryName;
}
