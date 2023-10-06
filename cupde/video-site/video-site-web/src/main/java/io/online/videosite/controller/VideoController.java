package io.online.videosite.controller;

import io.online.videosite.api.CommentService;
import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Comment;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.List;

/**
 * 视频控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:30:37
 */
@Controller
@RequestMapping(path = "/videohub")
@AllArgsConstructor
@Slf4j
public class VideoController {
    private final VideoService videoService;
    private final CommentService commentService;

    /**
     * 跳转到用户的视频列表页
     */
    @GetMapping(path = "/list")
    public ModelAndView list(@SessionAttribute(name = Constants.SESSION_USER_KEY, required = false) User user) {
        // 查询此用户所有的视频
        List<Video> videos = this.videoService.queryForUser(user);
        log.info("list(): 视频数量 = {}", videos.size());
        ModelAndView mav = new ModelAndView();
        mav.addObject("videos", videos);
        mav.setViewName("video/list");
        return mav;
    }

    /**
     * 跳转到查看页面
     */
    @GetMapping(path = "/show/{id}")
    public ModelAndView show(@PathVariable Integer id) {
        Video video = this.videoService.queryOneAndAddHits(id);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        List<Comment> comments = this.commentService.queryByVideoId(id);
        log.info("show(): 视频主键 = {}, 视频评论数量 = {}", id, comments.size());
        ModelAndView mav = new ModelAndView();
        mav.addObject("video", video);
        mav.addObject("comments", comments);
        mav.setViewName("video/show");
        return mav;
    }

    /**
     * 跳转到审核页面
     */
    @GetMapping(path = "/audit/{id}")
    public ModelAndView audit(@PathVariable Integer id) {
        Video video = this.videoService.queryOne(id, FetchType.EAGER);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("video", video);
        mav.setViewName("video/audit");
        return mav;
    }

    /**
     * 处理审核
     */
    @PostMapping(path = "/audit")
    public String handleAudit(@ModelAttribute Video param, @SessionAttribute(Constants.SESSION_USER_KEY) User user) {
        Video video = this.videoService.queryOne(param.getId(), FetchType.LAZY);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        // 只有视频状态为待审核，且提交的审核状态为通过 或 不通过，才进行处理
        if (video.getAuditStatus() == AuditStatus.PENDING &&
                (param.getAuditStatus() == AuditStatus.PASSED ||
                        param.getAuditStatus() == AuditStatus.UNPASSED)) {
            if (AuditStatus.UNPASSED == param.getAuditStatus()) {
                video.setAuditReason(param.getAuditReason());
            }
            video.setAuditStatus(param.getAuditStatus());
            this.videoService.audit(video, user);
        }
        return String.format("%s/videohub/audit/%s", UrlBasedViewResolver.REDIRECT_URL_PREFIX, param.getId());
    }
}
