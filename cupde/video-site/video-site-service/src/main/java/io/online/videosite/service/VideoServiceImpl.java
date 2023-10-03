package io.online.videosite.service;

import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.domain.Video;
import io.online.videosite.repository.VideoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:18:25
 */
@Service
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Override
    public List<Video> query(AuditStatus... auditStatus) {
        log.info("query(): auditStatus = {}", (Object) auditStatus);
        return this.videoRepository.findAll((root, query, builder) -> {
            if (auditStatus.length == 0) {
                return query.getRestriction();
            }
            return root.get("auditStatus").in(auditStatus);
        }, Sort.by(Direction.DESC, "videoHits"));
    }
}
