package com.example.demo.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户模型")
public class UserModel {
	@ApiModelProperty(value = "用户ID", required = true)
	private Integer userId;
	@ApiModelProperty(value = "用户名", required = true)
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
