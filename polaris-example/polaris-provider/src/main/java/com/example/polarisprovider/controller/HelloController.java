package com.example.polarisprovider.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.provider.api.domain.Hello;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例控制器
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午9:04:50
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
	@Autowired
	private ServerProperties server;

	@GetMapping("/get")
	public Hello get(@RequestParam String name) {
		log.info("提供者{}：get(): name = {}", this.server.getPort(), name);
		return new Hello()//
				.setId(this.server.getPort())//
				.setAge(18)//
				.setBirthday(LocalDate.now().minusYears(18))//
				.setName(name)//
				.setCreatetime(LocalDateTime.now());
	}
}
