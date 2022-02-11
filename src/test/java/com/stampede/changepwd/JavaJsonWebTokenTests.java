package com.stampede.changepwd;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaJsonWebTokenTests {
	@Test
	public void testJavaJwt() {
		try {
			long current = System.currentTimeMillis();
			String token = JWT.create()//
					.withJWTId(UUID.randomUUID().toString())//
					.withKeyId(UUID.randomUUID().toString())//
					.withIssuedAt(new Date(current))//
					.withExpiresAt(new Date(current + 5 * 60 * 1000)) // 设置过期时间
					.withAudience("user1") // 设置接受方信息，一般时登录用户
					.sign(Algorithm.HMAC256("111111")); // 使用HMAC算法，111111作为密钥加密
			System.err.println(token);
			String userId = JWT.decode(token).getAudience().get(0);
			System.err.println(userId);
			JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("111111")).build();
			DecodedJWT ret = jwtVerifier.verify(token);
			System.err.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(ret));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
