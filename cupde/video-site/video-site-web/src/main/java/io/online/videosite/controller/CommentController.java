package io.online.videosite.controller;

import io.online.videosite.api.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视频评论控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:28:20
 */
@Controller
@RequestMapping(path = "/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
}
