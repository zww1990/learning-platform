package com.stampede.changepwd.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public abstract class LdapPasswordUtils {
	public static String md5Password(String text) {
		try {
			String name = "MD5";
			MessageDigest instance = MessageDigest.getInstance(name);
			byte[] digest = instance.digest(text.getBytes(StandardCharsets.UTF_8));
			byte[] encode = Base64.getEncoder().encode(digest);
			String password = String.format("{%s}%s", name, new String(encode, StandardCharsets.UTF_8));
			return password;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
