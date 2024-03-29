package io.online.videosite.repository;

import io.online.videosite.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 视频数据访问对象接口
 *
 * @author 张维维
 * @since 2023-10-02 13:57:38
 */
public interface VideoRepository extends JpaRepository<Video, Integer>, JpaSpecificationExecutor<Video> {
}
