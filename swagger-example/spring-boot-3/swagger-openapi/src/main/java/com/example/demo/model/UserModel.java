package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * UserModel
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:56:49
 */
@Schema(description = "用户模型", requiredMode = Schema.RequiredMode.REQUIRED, title = "用户模型")
public class UserModel {
	@Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, title = "用户ID")
	@NotNull(message = "用户ID不能为空")
	@Positive(message = "用户ID必须大于0")
	private Integer userId;

	@Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, title = "用户名")
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
