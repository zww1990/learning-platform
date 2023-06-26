package com.example.demoprovider.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoprovider.config.Teacher;

import jakarta.annotation.Resource;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {
	@Resource
	private Teacher teacher;

	@GetMapping("/teacher")
	public String teacher() {
		return teacher.toString();
	}
}
