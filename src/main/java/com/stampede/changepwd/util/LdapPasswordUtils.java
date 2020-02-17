package com.stampede.changepwd.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:43:12
 * @description LDAP密码操作辅助类
 */
public abstract class LdapPasswordUtils {
	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:43:38
	 * @param text 明文密码
	 * @return 采用MD5的方式对明文密码进行加密
	 */
	public static String md5Password(String text) {
		try {
			String name = "MD5";
			MessageDigest instance = MessageDigest.getInstance(name);
			byte[] digest = instance.digest(text.getBytes(StandardCharsets.UTF_8));
			byte[] encode = Base64.getEncoder().encode(digest);
			String string = new String(encode, StandardCharsets.UTF_8);
			return String.format("{%s}%s", name, string);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
