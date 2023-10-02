package io.online.videosite.constant;

/**
 * 审核状态枚举类
 *
 * @author 张维维
 * @since 2023-10-02 13:36:13
 */
public enum AuditStatus {
    /**
     * 待审核
     */
    PENDING,
    /**
     * 审核不通过
     */
    UNPASSED,
    /**
     * 审核通过
     */
    PASSED,
}
