package com.example.demo.web.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户模型")
public class UserModel {
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	@Positive(message = "用户ID必须大于0")
	private Integer userId;
	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank(message = "用户名不能为空")
	private String userName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
