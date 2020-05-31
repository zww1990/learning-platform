package com.example.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.security.support.CaptchaBean;

/**
 * 控制器
 * 
 * @author home
 */
@Controller
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/")
	public String index(Authentication authentication) {
		log.info("getCredentials={}", authentication.getCredentials());
		log.info("getDetails={}", authentication.getDetails());
		log.info("getPrincipal={}", authentication.getPrincipal());
		log.info("getAuthorities={}", authentication.getAuthorities());
		log.info("getName={}", authentication.getName());
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/captcha")
	public void captcha(HttpSession session, HttpServletResponse response) throws IOException {
		CaptchaBean bean = new CaptchaBean();
		session.setAttribute(CaptchaBean.CAPTCHA_KEY, bean.output(response.getOutputStream()));
	}
}
