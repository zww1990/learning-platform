package com.example.security.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 控制器
 * 
 * @author home
 */
@Controller
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	@Resource
	private Producer producer;

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

	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}

	@GetMapping("/captcha")
	public void captcha(HttpSession session, HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		try (OutputStream out = response.getOutputStream()) {
			String text = this.producer.createText();
			session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
			ImageIO.write(this.producer.createImage(text), "jpg", out);
		}
	}
}
