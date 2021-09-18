package com.stampede.changepwd.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ZhangWeiWei
 * @date 2020年2月16日,下午10:45:03
 * @description 人员参数模型
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class PersonParam {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 旧密码
	 */
	private String password;
	/**
	 * 新密码
	 */
	private String newpassword;
	/**
	 * 确认密码
	 */
	private String repassword;
}
