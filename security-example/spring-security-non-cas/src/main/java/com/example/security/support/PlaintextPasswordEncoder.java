package com.example.security.support;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 纯文本密码编码器
 * 
 * @author home
 */
public class PlaintextPasswordEncoder implements PasswordEncoder {

	public PlaintextPasswordEncoder() {
		super();
	}

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
