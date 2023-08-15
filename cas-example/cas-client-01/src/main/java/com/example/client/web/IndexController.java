package com.example.client.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class IndexController {

	@GetMapping("/")
	public Object index(Principal principal) {
		log.info("当前用户：{}", principal);
		Map<String, Object> data = new HashMap<>();
		data.put("user", principal);
		return data;
	}
}
