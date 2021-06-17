package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/demo")
@Api(tags = "API示例")
public class DemoController {
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/get")
	@ApiOperation(value = "查询用户信息", notes = "按id查询用户信息")
	@ApiImplicitParams({ // NOSONAR
			@ApiImplicitParam(name = "id", value = "用户id"), // NOSONAR
			@ApiImplicitParam(name = "token", value = "密钥"),// NOSONAR
	})
	public UserModel get(@RequestParam Integer id, @ApiIgnore String text, @RequestHeader String token) {
		log.info("text：{}", text);
		log.info("id：{}", id);
		log.info("token: {}", token);
		UserModel user = new UserModel();
		user.setUserId(id);
		user.setUserName("李四");
		return user;
	}

	@PostMapping("/upload")
	@ApiOperation(value = "文件上传", notes = "上传多媒体文件")
	public List<Object> upload(// NOSONAR
			@ApiParam(value = "文件", required = true) @RequestPart MultipartFile file, // NOSONAR
			@Valid UserModel user, // NOSONAR
			@ApiParam(value = "密钥", required = true) @RequestHeader String token// NOSONAR
	) {
		log.info("文件参数：{}, {}, {}, {}, {}", file.isEmpty(), file.getContentType(), file.getName(),
				file.getOriginalFilename(), file.getSize());
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		log.info("token: {}", token);
		return Arrays.asList(file.isEmpty(), file.getContentType(), file.getName(), file.getOriginalFilename(),
				file.getSize(), user.getUserId(), user.getUserName());
	}

	@PutMapping("/update")
	@ApiOperation(value = "更新用户信息", notes = "按id更新用户信息")
	@ApiImplicitParams({ // NOSONAR
			@ApiImplicitParam(name = "token", value = "密钥"),// NOSONAR
	})
	public UserModel update(@Valid @RequestBody UserModel user, @RequestHeader String token) {
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		log.info("token: {}", token);
		return user;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除用户信息", notes = "按id删除用户信息")
	@ApiImplicitParams({ // NOSONAR
			@ApiImplicitParam(name = "id", value = "用户ID"), // NOSONAR
			@ApiImplicitParam(name = "token", value = "密钥"),// NOSONAR
	})
	public String delete(@PathVariable Integer id, @RequestHeader String token) {
		log.info("id：{}", id);
		log.info("token: {}", token);
		return "done";
	}
}
