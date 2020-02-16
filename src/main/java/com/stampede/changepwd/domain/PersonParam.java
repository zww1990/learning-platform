package com.stampede.changepwd.domain;

/**
 * @author ZhangWeiWei
 * @date 2020年2月16日,下午10:45:03
 * @description 人员参数模型
 */
public class PersonParam {
	private String username;
	private String password;
	private String newpassword;
	private String repassword;

	/**
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return 旧密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return 新密码
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * @return 确认密码
	 */
	public String getRepassword() {
		return repassword;
	}

	/**
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password 旧密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param newpassword 新密码
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @param repassword 确认密码
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
}
