package io.online.videosite.controller;

import io.online.videosite.api.CommentService;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Comment;
import io.online.videosite.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
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

    /**
     * 重定向到视频查看页面
     */
    @PostMapping(path = "/add")
    public String add(@ModelAttribute Comment comment,
                      @SessionAttribute(Constants.SESSION_USER_KEY) User user) {
        if (comment.getVideoId() != null && StringUtils.hasText(comment.getContent())) {
            this.commentService.save(comment, user);
        }
        return String.format("%s/video/show/%s",
                UrlBasedViewResolver.REDIRECT_URL_PREFIX, comment.getVideoId());
    }
}
