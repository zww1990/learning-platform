package io.online.videosite.domain;

import io.online.videosite.constant.AuditStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 视频实体类
 *
 * @author 张维维
 * @since 2023-10-02 13:32:54
 */
@Table(name = "t_video")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Video extends BaseEntity {
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频链接
     */
    private String videoLink;
    /**
     * 视频封面
     */
    private String videoLogo;
    /**
     * 视频点击率
     */
    private Integer videoHits;
    /**
     * 视频类别主键
     */
    private Integer categoryId;
    /**
     * 视频类别名称
     */
    @Transient
    private String categoryName;
    /**
     * 审核状态
     */
    @Enumerated(EnumType.STRING)
    private AuditStatus auditStatus;
    /**
     * 审核时间
     */
    private LocalDateTime auditedDate;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核人昵称
     */
    @Transient
    private String auditorNick;
    /**
     * 审核不通过原因
     */
    private String auditReason;
}
