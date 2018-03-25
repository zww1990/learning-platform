package com.example.demo.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/hello")
	public String hello(@AuthenticationPrincipal Authentication authentication) {
		System.err.println(authentication.getCredentials());
		System.err.println(authentication.getDetails());
		System.err.println(authentication.getName());
		System.err.println(authentication.getPrincipal());
		System.err.println(authentication.getAuthorities());
		return "hello";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
