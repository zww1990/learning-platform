package com.example.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
