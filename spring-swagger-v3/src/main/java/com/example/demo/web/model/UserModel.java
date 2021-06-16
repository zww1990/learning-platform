package com.example.demo.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户模型", required = true, title = "用户模型")
public class UserModel {
	@Schema(description = "用户ID", required = true, title = "用户ID")
	@NotNull(message = "用户ID不能为空")
	@Positive(message = "用户ID必须大于0")
	private Integer userId;
	@Schema(description = "用户名", required = true, title = "用户名")
	@NotBlank(message = "用户名不能为空")
	private String userName;

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
