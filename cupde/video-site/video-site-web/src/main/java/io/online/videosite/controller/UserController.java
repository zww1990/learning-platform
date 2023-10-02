package io.online.videosite.controller;

import io.online.videosite.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:29:35
 */
@Controller
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
}
