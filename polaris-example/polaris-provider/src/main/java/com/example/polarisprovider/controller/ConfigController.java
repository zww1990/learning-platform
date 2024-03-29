package com.example.polarisprovider.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polarisprovider.config.Teacher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置控制器
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午7:37:59
 */
@RestController
@RequestMapping("/config")
@Slf4j
@AllArgsConstructor
public class ConfigController {
	private final Teacher teacher;
	private final ServerProperties server;

	@GetMapping("/get")
	public String get() {
		log.info("提供者{}:get(): {}", this.server.getPort(), this.teacher);
		return this.teacher.toString();
	}
}
