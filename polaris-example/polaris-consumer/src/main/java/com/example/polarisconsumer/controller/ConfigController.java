package com.example.polarisconsumer.controller;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polarisconsumer.config.Student;

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
	private final Student student;
	private final ServerProperties server;

	@GetMapping("/get")
	public String get() {
		log.info("消费者{}:get(): {}", this.server.getPort(), this.student);
		return this.student.toString();
	}
}
