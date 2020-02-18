package com.stampede.changepwd;

import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JsonWebTokenTests {
	@Test
	public void testEncode() {
		try {
			System.out.println(Jwts.builder().setId(UUID.randomUUID().toString()).setSubject("zww")// 用户名
					.setIssuedAt(new Date())// 签发时间
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))// 过期时间：5分钟
					.signWith(SignatureAlgorithm.HS256, "hello").compact());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDecode() {
		try {
			String string = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NWM3NDliMC0wYzA1LTQwMjktYTgwNy0wOWNkMjBkOGNmNmIiLCJzdWIiOiJ6d3ciLCJpYXQiOjE1ODIwMTgyOTUsImV4cCI6MTU4MjAxODMyNX0.JteLPk4f9BjTVilRPdANbFbxSrNixWrvrEqm0TO0Kb0";
			System.out.println(Jwts.parser().setSigningKey("hello").parseClaimsJws(string).getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
