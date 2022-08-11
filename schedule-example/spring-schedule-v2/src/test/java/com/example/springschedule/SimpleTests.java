package com.example.springschedule;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

/**
 * Simple Tests
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午2:31:25
 */
public class SimpleTests {
	@Test
	public void testUrlEncode() {
		try {
			String url = "/rester/hello-20220811.txt";
			System.err.println(URLEncoder.encode(url, StandardCharsets.UTF_8.name()));
			System.err.println(UriUtils.encodePathSegment(url, StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBase64() {
		try {
			String content = "最伟大的作品";
			System.err.println(Base64Utils.encodeToString(content.getBytes()));
			System.err.println(Base64Utils.encodeToString(content.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
