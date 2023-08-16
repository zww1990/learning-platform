package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserModel
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:56:49
 */
@Getter
@Setter
@ToString
@Schema(description = "用户模型", required = true, title = "用户模型")
public class UserModel {
	@Schema(description = "用户ID", required = true, title = "用户ID")
	@NotNull(message = "用户ID不能为空")
	@Positive(message = "用户ID必须大于0")
	private Integer userId;
	@Schema(description = "用户名", required = true, title = "用户名")
	@NotBlank(message = "用户名不能为空")
	private String userName;

}
