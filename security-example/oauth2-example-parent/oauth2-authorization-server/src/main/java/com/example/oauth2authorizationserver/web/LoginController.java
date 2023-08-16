package com.example.oauth2authorizationserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午5:02:07
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		// 跳转到登录页面
		return "login";
	}

}
