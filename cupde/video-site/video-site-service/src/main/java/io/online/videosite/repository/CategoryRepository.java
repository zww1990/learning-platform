package io.online.videosite.repository;

import io.online.videosite.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 视频类别实体 - 数据访问对象接口
 *
 * @author 张维维
 * @since 2023-10-02 13:56:42
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
