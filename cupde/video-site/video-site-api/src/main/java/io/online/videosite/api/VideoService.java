package io.online.videosite.api;

import io.online.videosite.constant.AuditStatus;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import jakarta.persistence.FetchType;

import java.util.List;

/**
 * 视频服务接口
 *
 * @author 张维维
 * @since 2023-10-02 14:17:31
 */
public interface VideoService {
    /**
     * 按审核状态查询视频，并按点击量进行降序排序
     *
     * @param categoryId  类别主键
     * @param auditStatus {@link AuditStatus}
     * @return {@link List<Video>}
     * @author 张维维
     * @since 2023-10-03 16:33:50
     */
    List<Video> query(Integer categoryId, AuditStatus... auditStatus);

    /**
     * 查询此用户所有的视频
     *
     * @param user {@link User}
     * @return {@link List<Video>}
     * @author 张维维
     * @since 2023-10-04 11:27:51
     */
    List<Video> queryForUser(User user);

    /**
     * 按主键查询
     *
     * @param id        主键
     * @param fetchType {@link FetchType}
     * @return {@link Video}
     * @author 张维维
     * @since 2023-10-04 21:13:49
     */
    Video queryOne(Integer id, FetchType fetchType);

    /**
     * 按主键查询，并增加点击量
     *
     * @param id 主键
     * @return {@link Video}
     * @author 张维维
     * @since 2023-10-04 21:13:49
     */
    Video queryOneAndAddHits(Integer id);

    /**
     * 视频审核
     *
     * @param video {@link Video}
     * @param user  {@link User}
     * @author 张维维
     * @since 2023-10-05 17:04:25
     */
    void audit(Video video, User user);
}