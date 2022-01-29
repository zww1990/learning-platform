package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 控制器
 * 
 * @author home
 */
@Controller
@Slf4j
public class IndexController {

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
