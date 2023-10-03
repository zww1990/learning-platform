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

    @GetMapping(path = {"", "/"})
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        log.info("index(): user = {}", user);
        List<Video> videos = this.videoService.query(AuditStatus.PASSED);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("videos", videos);
        return mav;
    }
}
