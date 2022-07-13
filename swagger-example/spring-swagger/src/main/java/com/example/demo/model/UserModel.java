package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UserModel
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:25:09
 */
@ApiModel(description = "用户模型")
@Getter
@Setter
@ToString
public class UserModel {
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	@Positive(message = "用户ID必须大于0")
	private Integer userId;
	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank(message = "用户名不能为空")
	private String userName;

}
