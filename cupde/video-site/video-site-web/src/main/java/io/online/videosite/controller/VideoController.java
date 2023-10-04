package io.online.videosite.controller;

import io.online.videosite.api.VideoService;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 视频控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:30:37
 */
@Controller
@RequestMapping(path = "/video")
@AllArgsConstructor
@Slf4j
public class VideoController {
    private final VideoService videoService;

    /**
     * 跳转到用户的视频列表页
     */
    @GetMapping(path = "/list")
    public ModelAndView list(@SessionAttribute(name = Constants.SESSION_USER_KEY, required = false) User user) {
        ModelAndView mav = new ModelAndView();
        if (user == null) {
            mav.setStatus(HttpStatus.UNAUTHORIZED);
            mav.setViewName("error/401");
            return mav;
        }
        // 查询此用户所有的视频
        List<Video> videos = this.videoService.queryForUser(user);
        log.info("list(): 视频数量 = {}", videos.size());
        mav.addObject("videos", videos);
        mav.setViewName("video/list");
        return mav;
    }
}
