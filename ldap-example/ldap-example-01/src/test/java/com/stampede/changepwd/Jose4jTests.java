package com.stampede.changepwd;

import java.security.Key;
import java.util.UUID;

import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey.OutputControlLevel;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Jose4jTests {

	private String keyId;
	private String publicKey;
	private String privateKey;
	private String token;

	@BeforeEach
	public void createKeyPair() {
		try {
			keyId = UUID.randomUUID().toString();
			RsaJsonWebKey jwk = RsaJwkGenerator.generateJwk(2048);
			jwk.setKeyId(keyId);
			jwk.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
			publicKey = jwk.toJson(OutputControlLevel.PUBLIC_ONLY);
			privateKey = jwk.toJson(OutputControlLevel.INCLUDE_PRIVATE);
			System.err.println("keyId=" + keyId);
			System.err.println("publicKey=" + publicKey);
			System.err.println("privateKey=" + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createToken() {
		try {
			JwtClaims claims = new JwtClaims();
			claims.setGeneratedJwtId();
			claims.setIssuedAtToNow();
			NumericDate expirationTime = NumericDate.now();
			expirationTime.addSeconds(60 * 1);
			claims.setExpirationTime(expirationTime);
			claims.setNotBeforeMinutesInThePast(1);
			claims.setSubject("userlogin");
			claims.setIssuer("张维维");
			claims.setStringClaim("username", "zhangweiwei");
			JsonWebSignature jws = new JsonWebSignature();
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
			jws.setKeyIdHeaderValue(keyId);
			jws.setPayload(claims.toJson());
			Key key = new RsaJsonWebKey(JsonUtil.parseJson(privateKey)).getPrivateKey();
			jws.setKey(key);
			token = jws.getCompactSerialization();
			System.err.println("token=" + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void checkToken() {
		try {
			JwtConsumer consumer = new JwtConsumerBuilder()//
					.setRequireExpirationTime()//
					.setRequireIssuedAt()//
					.setRequireJwtId()//
					.setRequireNotBefore()//
					.setRequireSubject()//
					.setVerificationKey(new RsaJsonWebKey(JsonUtil.parseJson(publicKey)).getPublicKey())//
					.build();
			JwtClaims claims = consumer.processToClaims(token);
			System.err.println(claims.getClaimValueAsString("username"));
			System.err.println(claims.getStringClaimValue("username"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
