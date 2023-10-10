package io.online.videosite.controller;

import io.online.videosite.api.UserService;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.regex.Pattern;

/**
 * 用户控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:29:35
 */
@Controller
@RequestMapping(path = "/user")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    // 匹配非大小写字母、数字、下划线
    private final Pattern pattern = Pattern.compile("\\W");

    /**
     * 处理用户登录
     */
    @PostMapping(path = "/login")
    public ModelAndView login(@ModelAttribute User user, HttpSession session) {
        log.info("login(): user = {}", user);
        // 为了减少多次书写跳转到登录页，这里默认设置跳转到登录页
        ModelAndView mav = new ModelAndView("user/login");
        if (!StringUtils.hasText(user.getUsername())) {
            mav.addObject("error", "请输入用户名！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (!StringUtils.hasText(user.getPassword())) {
            mav.addObject("error", "请输入密码！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        User entity = this.userService.query(user);
        if (entity == null) {
            log.info("login(): 此用户 {} 不存在！", user.getUsername());
            mav.addObject("error", "用户名或密码不正确！");
            mav.setStatus(HttpStatus.UNAUTHORIZED);
            return mav;
        }
        // 验证密码
        boolean matches = this.passwordEncoder.matches(user.getPassword(), entity.getPassword());
        if (!matches) {
            log.info("login(): 此用户 {} 密码不正确！", user.getUsername());
            mav.addObject("error", "用户名或密码不正确！");
            mav.setStatus(HttpStatus.UNAUTHORIZED);
            return mav;
        }
        entity.setPassword(null);// 清除密码
        session.setAttribute(Constants.SESSION_USER_KEY, entity);// 在会话中保存登录用户
        mav.setViewName(UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/");// 重定向到主页
        return mav;
    }

    /**
     * 处理用户注册
     */
    @PostMapping(path = "/register")
    public ModelAndView register(@ModelAttribute User user) {
        log.info("register(): user = {}", user);
        // 为了减少多次书写跳转到注册页，这里默认设置跳转到注册页
        ModelAndView mav = new ModelAndView("user/register");
        if (!StringUtils.hasText(user.getUsername())) {
            mav.addObject("error", "请输入用户名！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (this.pattern.matcher(user.getUsername()).find()) {
            mav.addObject("error", "只允许输入：大写或小写字母、数字、下划线！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (!StringUtils.hasText(user.getNickname())) {
            mav.addObject("error", "请输入昵称！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (!StringUtils.hasText(user.getPassword())) {
            mav.addObject("error", "请输入密码！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (!StringUtils.hasText(user.getPassword2())) {
            mav.addObject("error", "请再次确认密码！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (!user.getPassword().equals(user.getPassword2())) {
            mav.addObject("error", "两次输入的密码不一致！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        User entity = this.userService.query(user);
        if (entity != null) {
            log.info("login(): 此用户 {} 已存在！", user.getUsername());
            mav.addObject("error", String.format("此用户 %s 已存在！", user.getUsername()));
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));// 密码加密
        this.userService.save(user);
        mav.setViewName(UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/user/success");// 重定向到成功页
        return mav;
    }
}
