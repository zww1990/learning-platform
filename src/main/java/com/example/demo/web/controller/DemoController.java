package com.example.demo.web.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.web.config.UserInfo;
import com.example.demo.web.model.UserModel;

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
	@ApiImplicitParam(name = "id", value = "用户id", paramType = "query", dataType = "Integer", required = true)
	public UserModel get(@RequestParam Integer id, @ApiIgnore String text) {
		System.err.println(text);
		log.info("id：{}", id);
		UserModel user = new UserModel();
		user.setUserId(id);
		user.setUserName("李四");
		return user;
	}

	@PutMapping("/user")
	@ApiOperation(value = "请求头传参", notes = "设置请求头User-Info作为参数的名称，参数值是json字符串，需要经过base64转码，否则视为无效参数")
	@ApiImplicitParam(name = UserInfo.DEFAULT_NAME, value = "用户信息", paramType = "header", required = true)
	public UserModel user(@ApiIgnore @UserInfo UserModel user) {
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		return user;
	}

	@PostMapping("/upload")
	@ApiOperation(value = "文件上传", notes = "文件上传的请求内容类型必须是multipart/form-data")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataType = "Integer", required = true),
			@ApiImplicitParam(name = "userName", value = "用户名", paramType = "form", dataType = "String", required = true) })
	public List<? extends Object> upload(
			@ApiParam(name = "file", value = "文件", required = true) @RequestPart MultipartFile file, UserModel user) {
		log.info("文件参数：{}, {}, {}, {}, {}", file.isEmpty(), file.getContentType(), file.getName(),
				file.getOriginalFilename(), file.getSize());
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		return Arrays.asList(file.isEmpty(), file.getContentType(), file.getName(), file.getOriginalFilename(),
				file.getSize(), user.getUserId(), user.getUserName());
	}

	@PostMapping("/json")
	@ApiOperation(value = "提交json格式数据", notes = "提交json格式数据时，请求内容类型必须是application/json")
	@ApiImplicitParam(name = "user", value = "用户模型", paramType = "body", dataType = "UserModel", required = true)
	public UserModel json(@RequestBody UserModel user) {
		log.info("用户ID：{}，用户名：{}", user.getUserId(), user.getUserName());
		return user;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除用户信息", notes = "按id删除用户信息，使用路径传递id")
	@ApiImplicitParam(name = "id", value = "用户id", paramType = "path", dataType = "Integer", required = true)
	public String delete(@PathVariable Integer id) {
		log.info("id：{}", id);
		return "done";
	}
}
