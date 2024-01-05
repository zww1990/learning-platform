package io.online.videosite.repository;

import io.online.videosite.domain.VideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 视频观看历史数据访问对象接口
 *
 * @author 张维维
 * @since 2024-01-05 14:40:13
 */
public interface VideoHistoryRepository extends JpaRepository<VideoHistory, Integer>,
        JpaSpecificationExecutor<VideoHistory> {
}
