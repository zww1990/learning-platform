package com.example.oauth2client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 默认控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:28:11
 */
@Controller
public class DefaultController {

	@GetMapping("/")
	public String root() {
		// 重定向首页
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String index() {
		// 跳转到首页
		return "index";
	}

	@GetMapping("/logged-out")
	public String loggedOut() {
		// 跳转到注销页面
		return "logged-out";
	}

}
