package io.online.videosite.controller;

import io.online.videosite.api.CommentService;
import io.online.videosite.api.VideoService;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Comment;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

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
    private final VideoService videoService;

    /**
     * 处理添加评论
     */
    @PostMapping(path = "/add")
    public String add(@ModelAttribute Comment comment,
                      @SessionAttribute(Constants.SESSION_USER_KEY) User user,
                      @RequestParam String redirect) {
        if (comment.getVideoId() != null && StringUtils.hasText(comment.getContent())) {
            Video video = this.videoService.queryOne(comment.getVideoId(), FetchType.LAZY);
            // 如果视频不存在
            if (video == null) {
                throw new EntityNotFoundException("此视频不存在");
            }
            this.commentService.save(comment, user);
        }
        return String.format("%s/videohub/show/%s?redirect=%s",
                UrlBasedViewResolver.REDIRECT_URL_PREFIX, comment.getVideoId(), redirect);
    }
}
