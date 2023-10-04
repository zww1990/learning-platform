package io.online.videosite.api;

import io.online.videosite.constant.AuditStatus;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;

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
}
