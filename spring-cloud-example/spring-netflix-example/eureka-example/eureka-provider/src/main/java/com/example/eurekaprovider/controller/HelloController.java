package com.example.eurekaprovider.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.provider.api.domain.FileInfo;
import com.example.provider.api.domain.Hello;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class HelloController {
	private final ServerProperties server;

	@GetMapping("/get")
	public Hello get(@RequestParam String name) {
		log.info("提供者{}：get(): name = {}", this.server.getPort(), name);
		return new Hello()//
				.setId(this.server.getPort())//
				.setAge(18)//
				.setBirthday(LocalDate.now().minusYears(18))//
				.setCreatetime(LocalDateTime.now())//
				.setName(name);
	}

	@PostMapping("/upload")
	public FileInfo upload(@RequestPart MultipartFile file) {
		String contentType = file.getContentType();
		String name = file.getName();
		String originalFilename = file.getOriginalFilename();
		long size = file.getSize();
		log.info("提供者：upload(): contentType = {}, name = {}, originalFilename = {}, size = {}", //
				contentType, name, originalFilename, size);
		return new FileInfo()//
				.setId(UUID.randomUUID().toString())//
				.setName(name)//
				.setSize(size)//
				.setContentType(contentType)//
				.setOriginalFilename(originalFilename);
	}
}
