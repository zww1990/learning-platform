package io.online.videosite.controller;

import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 主页控制器
 *
 * @author 张维维
 * @since 2023-10-03 16:05:32
 */
@Controller
@Slf4j
@AllArgsConstructor
public class IndexController {
    private final VideoService videoService;

    /**
     * 跳转到主页
     */
    @GetMapping(path = {"", "/"})
    public ModelAndView index(HttpSession session) {
        // 从会话中获取当前登录用户
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        List<Video> videos = this.videoService.query(AuditStatus.PASSED);
        log.info("index(): user = {}, video size = {}", user, videos.size());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("videos", videos);
        return mav;
    }

    /**
     * 跳转到登录页
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login").addObject("user", new User());
    }
}
