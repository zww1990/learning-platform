package io.online.videosite.api;

import io.online.videosite.domain.Comment;

import java.util.List;

/**
 * 视频评论服务接口
 *
 * @author 张维维
 * @since 2023-10-02 14:14:11
 */
public interface CommentService {
    /**
     * 按视频主键查询评论列表
     *
     * @param videoId 视频主键
     * @return {@link List<Comment>}
     * @author 张维维
     * @since 2023-10-05 11:31:30
     */
    List<Comment> queryByVideoId(Integer videoId);
}
