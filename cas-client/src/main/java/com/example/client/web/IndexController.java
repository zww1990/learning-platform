package com.example.client.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/")
	public Object index(Principal principal) {
		log.info("当前用户：{}", principal);
		Map<String, Object> data = new HashMap<>();
		data.put("user", principal);
		return data;
	}
}
