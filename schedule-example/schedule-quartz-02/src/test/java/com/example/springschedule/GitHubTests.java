package com.example.springschedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.HttpException;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * GitHubTests
 * 
 * @author zhang weiwei
 * @since 2022年12月10日,下午4:52:47
 */
@Slf4j
public class GitHubTests {
	@Test
	public void testGitHub() {
		String accessToken = "";
		try {
			GitHub connect = GitHub.connectUsingOAuth(accessToken);
			log.info("正在使用oauth连接到GitHub");
			if (connect.isCredentialValid()) {
				log.info("凭据有效，认证[{}]成功。", connect.getApiUrl());
				GHRepository repository = connect.getRepository("zww1990/learning-platform");
				log.info("正在获取存储库[{}]", repository.getHttpTransportUrl());
				LocalDateTime now = LocalDateTime.now().withNano(0);
				String path = String.format("design-pattern-example/src/main/resources/%s.txt",
						DateTimeFormatter.BASIC_ISO_DATE.format(now));
				String content = String.format("hello world %s", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
				try {
					log.info("正在获取文件内容[{}]", path);
					repository.getFileContent(path).update(content, content);
					log.info("更新内容成功[{}]", content);
				} catch (GHFileNotFoundException e) {
					log.info("正在创建文件[{}]", path);
					repository.createContent()//
							.path(path)//
							.content(content)//
							.message(content)//
							.commit();
					log.info("提交内容成功[{}]", content);
				}
			} else {
				log.error("{}", HttpStatus.UNAUTHORIZED);
			}
		} catch (GHFileNotFoundException e) {
			log.error("{} - {}", HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		} catch (HttpException e) {
			log.error("{} - {}", HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
