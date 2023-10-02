package io.online.videosite.domain;

import jakarta.persistence.Table;
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
@Getter
@Setter
@ToString
public class Comment extends BaseEntity {
    /**
     * 评论内容
     */
    private String content;
    /**
     * 视频主键
     */
    private Integer videoId;
}
