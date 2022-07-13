package com.stampede.changepwd.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:43:12
 * @description LDAP密码操作辅助类
 */
public abstract class LdapPasswordUtils {
	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

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

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午6:04:43
	 * @param text 明文密码
	 * @return 统计字符串包含小写字母个数
	 */
	public static long countLowerCase(String text) {
		return text.chars().filter(Character::isLowerCase).count();
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午6:05:44
	 * @param text 明文密码
	 * @return 统计字符串包含大写字母个数
	 */
	public static long countUpperCase(String text) {
		return text.chars().filter(Character::isUpperCase).count();
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午6:06:12
	 * @param text 明文密码
	 * @return 统计字符串包含数字个数
	 */
	public static long countDigit(String text) {
		return text.chars().filter(Character::isDigit).count();
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午6:22:17
	 * @param text 明文密码
	 * @return 统计字符串的字符种类个数
	 */
	public static int countCharacterType(String text) {
		int count = 0;
		if (text.chars().anyMatch(Character::isDigit)) {
			count++;
		}
		if (text.chars().anyMatch(Character::isLowerCase)) {
			count++;
		}
		if (text.chars().anyMatch(Character::isUpperCase)) {
			count++;
		}
		return count;
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,下午5:47:17
	 * @param username 用户名
	 * @param timeout 有效期：分钟
	 * @return 采用JWT方式进行字符串编码
	 */
	public static String jwtEncode(String username, long timeout) {
		long current = System.currentTimeMillis();
		return Jwts.builder().setId(UUID.randomUUID().toString())// 主键
				.setSubject(username)// 主题
				.setIssuedAt(new Date(current))// 签发时间
				.setExpiration(new Date(current + timeout * 60 * 1000))// 到期时间
				.signWith(KEY)// 签名算法密钥
				.compact();
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,下午5:53:47
	 * @param token JWT编码
	 * @return 采用JWT方式进行解码
	 */
	public static Claims jwtDecode(String token) {
		return Jwts.parserBuilder().setSigningKey(KEY)// 签名算法密钥
				.build().parseClaimsJws(token)// 解析JWT字符串
				.getBody();
	}
}
