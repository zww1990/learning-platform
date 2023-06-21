package com.example.democonsumer.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.democonsumer.config.Student;

import jakarta.annotation.Resource;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {
	@Resource
	private Student student;

	@GetMapping("/student")
	public String student() {
		return this.student.toString();
	}
}
