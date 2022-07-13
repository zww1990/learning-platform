package org.jasig.cas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.jasig.cas.authentication.handler.DefaultPasswordEncoder;
import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.codec.Hex;

public class MessageDigestTests {

	@Test
	public void testMd5Password() {
		try {
			String text = "hello";
			MessageDigest instance = MessageDigest.getInstance("MD5");
			byte[] bytes = instance.digest(text.getBytes(StandardCharsets.UTF_8));
			System.err.println(new String(Hex.encode(bytes)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEncode() {
		try {
			DefaultPasswordEncoder encoder = new DefaultPasswordEncoder("MD5");
			encoder.setCharacterEncoding(StandardCharsets.UTF_8.name());
			System.err.println(encoder.encode("hello"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEncodePassword() {
		try {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			System.err.println(encoder.encodePassword("hello", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
