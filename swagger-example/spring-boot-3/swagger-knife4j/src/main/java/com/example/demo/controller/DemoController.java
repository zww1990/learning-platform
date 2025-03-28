package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.UserModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/demo")
@Tag(name = "API示例")
public class DemoController {
	public static Logger log = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/get")
	@Operation(summary = "查询用户信息", description = "按id查询用户信息")
	@Parameters({
			@Parameter(name = "id", description = "用户id"),
			@Parameter(name = "token", description = "密钥"),
	})
	public UserModel get(@RequestParam Integer id, @Parameter(hidden = true) String text, @RequestHeader String token) {
		log.info("text：{}", text);
		log.info("id：{}", id);
		log.info("token: {}", token);
		UserModel user = new UserModel();
		user.setUserId(id);
		user.setUserName("李四");
		return user;
	}

	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "文件上传", description = "上传多媒体文件")
	@Parameters({
			@Parameter(name = "userId", description = "用户ID", required = true, in = ParameterIn.QUERY),
			@Parameter(name = "userName", description = "用户名", required = true, in = ParameterIn.QUERY),
	})
	public List<Object> upload(
							   @Parameter(description = "文件", required = true) @RequestPart MultipartFile file,
							   @Valid UserModel user,
							   @Parameter(description = "密钥", required = true) @RequestHeader String token
	) {
		log.info("文件参数：{}, {}, {}, {}, {}", file.isEmpty(), file.getContentType(), file.getName(),
				file.getOriginalFilename(), file.getSize());
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		log.info("token: {}", token);
		return Arrays.asList(file.isEmpty(), file.getContentType(), file.getName(), file.getOriginalFilename(),
				file.getSize(), user.getUserId(), user.getUserName());
	}

	@PutMapping("/update")
	@Operation(summary = "更新用户信息", description = "按id更新用户信息")
	@Parameters({
			@Parameter(name = "token", description = "密钥"),
	})
	public UserModel update(@Valid @RequestBody UserModel user, @RequestHeader String token) {
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		log.info("token: {}", token);
		return user;
	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "删除用户信息", description = "按id删除用户信息")
	@Parameters({
			@Parameter(name = "id", description = "用户ID"),
			@Parameter(name = "token", description = "密钥"),
	})
	public String delete(@PathVariable Integer id, @RequestHeader String token) {
		log.info("id：{}", id);
		log.info("token: {}", token);
		return "done";
	}
}
