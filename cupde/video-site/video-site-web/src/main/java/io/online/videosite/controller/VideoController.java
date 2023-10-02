package io.online.videosite.controller;

import io.online.videosite.api.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视频控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:30:37
 */
@Controller
@RequestMapping(path = "/video")
@AllArgsConstructor
public class VideoController {
    private final VideoService videoService;
}
