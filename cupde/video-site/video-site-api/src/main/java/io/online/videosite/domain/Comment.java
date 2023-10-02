package io.online.videosite.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 视频评论实体类
 *
 * @author 张维维
 * @since 2023-10-02 13:44:37
 */
@Table(name = "t_comment")
@Entity
@Getter
@Setter
@ToString
public class Comment extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 视频主键
     */
    private Integer videoId;
}
