package com.stampede.changepwd;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

public class FusionAuthJwtTests {
	@Test
	public void testJwt() {
		try {
			// 使用 SHA-256 哈希构建 HMAC 签名者
			Signer signer = HMACSigner.newSHA256Signer("too many secrets");

			// 使用 issuer(iss)、issuer(iat)、subject(sub) 和 expire(exp) 构建一个新的 JWT
			JWT jwt = new JWT()//
					.setIssuer("www.acme.com")//
					.setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))//
					.setSubject(UUID.randomUUID().toString())//
					.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));

			System.err.println(jwt);

			// 将 JWT 签名并编码为 JSON 字符串表示
			String encodedJWT = JWT.getEncoder().encode(jwt, signer);

			// 使用用于签署 JWT 的相同密钥构建 HMC 验证程序
			Verifier verifier = HMACVerifier.newVerifier("too many secrets");

			// 验证编码字符串 JWT 并将其解码为丰富的对象
			jwt = JWT.getDecoder().decode(encodedJWT, verifier);

			System.err.println(jwt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
