package com.example.springschedule;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import com.example.springschedule.service.exchange.CreateNewFileRequest;
import com.example.springschedule.service.exchange.UpdateFileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple Tests
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午2:31:25
 */
public class SimpleTests {
	@Test
	public void testPaths() {
		try {
			System.err.println(Paths.get("d:/projects/learning-platform", "design-pattern-example/src/main/resources",
					String.format("%s.txt", DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()))));
			System.err.println(Paths.get("d:/projects/learning-platform",
					String.format("design-pattern-example/src/main/resources/%s.txt",
							DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDateTimeFormatter() {
		try {
			System.err.println(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDateTime.now().withNano(0)));
			System.err.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now().withNano(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	@Test
	public void testWriteJson() {
		try {
			ObjectMapper json = new ObjectMapper();
			CreateNewFileRequest cnfr = new CreateNewFileRequest();
			UpdateFileRequest ufr = new UpdateFileRequest();
			System.err.println(cnfr);
			System.err.println(json.writerWithDefaultPrettyPrinter()//
					.writeValueAsString(cnfr));
			System.err.println("----------------------------------");
			System.err.println(ufr);
			System.err.println(json.writerWithDefaultPrettyPrinter()//
					.writeValueAsString(ufr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
