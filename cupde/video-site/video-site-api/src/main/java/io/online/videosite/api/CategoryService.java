package io.online.videosite.api;

import io.online.videosite.domain.Category;

import java.util.List;

/**
 * 视频类别服务接口
 *
 * @author 张维维
 * @since 2023-10-02 14:11:00
 */
public interface CategoryService {
    /**
     * 查询所有视频类别
     *
     * @return {@link List<Category>}
     * @author 张维维
     * @since 2023-10-04 11:13:01
     */
    List<Category> query();
}
