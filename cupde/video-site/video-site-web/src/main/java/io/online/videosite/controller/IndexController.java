package io.online.videosite.controller;

import io.online.videosite.api.CategoryService;
import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Category;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

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
    private final CategoryService categoryService;

    /**
     * 跳转到主页
     */
    @GetMapping(path = {"", "/"})
    public ModelAndView index(
            // 从会话中获取当前登录用户
            @SessionAttribute(name = Constants.SESSION_USER_KEY, required = false) User user) {
        // 查询所有用户审核通过的视频
        List<Video> videos = this.videoService.query(AuditStatus.PASSED);
        List<Category> categories = this.categoryService.query();
        log.info("index(): 用户 = {}, 视频数量 = {}, 类别数量 = {}", user, videos.size(), categories.size());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("videos", videos);
        int limit = 10;// 控制类别展示数量
        if (categories.size() > limit) {
            mav.addObject("categories", categories.subList(0, limit));
            mav.addObject("categoryMoreList", categories.subList(limit, categories.size()));
        } else {
            mav.addObject("categories", categories);
        }
        return mav;
    }

    /**
     * 跳转到登录页
     */
    @GetMapping(path = "/login")
    public ModelAndView login() {
        return new ModelAndView("user/login").addObject("user", new User());
    }

    /**
     * 跳转到注册页
     */
    @GetMapping(path = "/register")
    public ModelAndView register() {
        return new ModelAndView("user/register").addObject("user", new User());
    }

    /**
     * 跳转到主页
     */
    @GetMapping(path = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        // 重定向
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/";
    }
}
