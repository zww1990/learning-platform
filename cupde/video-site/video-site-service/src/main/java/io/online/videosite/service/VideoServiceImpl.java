package io.online.videosite.service;

import io.online.videosite.api.VideoService;
import io.online.videosite.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 视频服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:18:25
 */
@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
}
