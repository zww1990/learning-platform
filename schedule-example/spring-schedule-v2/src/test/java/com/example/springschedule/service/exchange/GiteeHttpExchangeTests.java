package com.example.springschedule.service.exchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springschedule.config.ApplicationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * GiteeHttpExchangeTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月5日,下午3:01:06
 */
@SpringBootTest
public class GiteeHttpExchangeTests {
	@Autowired
	private GiteeHttpExchange giteeHttpExchange;
	@Autowired
	private ApplicationConfig applicationConfig;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetContent() {
		try {
			String owner = "zww1990";
			String repo = "learning-platform";
			String path = "design-pattern-example/src/main/resources/aa.txt";
			String accessToken = this.applicationConfig.getGitConfig().getAccessToken();
			System.err.println(
					this.giteeHttpExchange.defaultGetContent(owner, repo, path, accessToken, this.objectMapper));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateNewFile() {
		try {
			String owner = "zww1990";
			String repo = "learning-platform";
			String path = "design-pattern-example/src/main/resources/dd.txt";
			CreateNewFileRequest request = new CreateNewFileRequest();
			request.setAccessToken(this.applicationConfig.getGitConfig().getAccessToken());
			request.setContent("aGVsbG8gd29ybGQgMTY3MDIxODYxMDAwNQ==");
			request.setMessage("hello world");
			System.err.println(this.giteeHttpExchange.createNewFile(owner, repo, path, request));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateFile() {
		try {
			String owner = "zww1990";
			String repo = "learning-platform";
			String path = "design-pattern-example/src/main/resources/bb.txt";
			UpdateFileRequest request = new UpdateFileRequest();
			request.setAccessToken(this.applicationConfig.getGitConfig().getAccessToken());
			request.setContent("aGVsbG8gd29ybGQgMTY3MDIxODYxMDAwNQ==");
			request.setMessage("hello world");
			request.setSha("");
			System.err.println(this.giteeHttpExchange.updateFile(owner, repo, path, request));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
