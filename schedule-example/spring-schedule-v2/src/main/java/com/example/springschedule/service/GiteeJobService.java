package com.example.springschedule.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.springschedule.config.ApplicationConfig;
import com.example.springschedule.config.ApplicationConfig.GitConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * Gitee Job Service
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午12:40:13
 */
@DisallowConcurrentExecution
@Slf4j
public class GiteeJobService extends QuartzJobBean {
	@Resource
	private ApplicationConfig appConfig;
	@Resource
	private RestTemplate restTemplate;

	@SuppressWarnings("deprecation")
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GitConfig gitConfig = this.appConfig.getGitConfig();
		if (!this.validated(gitConfig)) {
			return;
		}
		// 文件的路径
		String path = String.format("%s/%s/%s.txt", gitConfig.getCreateNewFileUrl(), gitConfig.getFilePattern(),
				LocalDate.now().format(DateTimeFormatter.ofPattern(gitConfig.getDatePattern())));
		// 提交信息
		String message = String.format("hello world %s", System.currentTimeMillis());
		// 文件内容, 要用 base64 编码
		String content = Base64Utils.encodeToString(message.getBytes(StandardCharsets.UTF_8));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		Map<String, String> params = new HashMap<>();
		params.put("access_token", gitConfig.getAccessToken());
		params.put("content", content);
		params.put("message", message);
		log.info("Gitee API >> 仓库 >> 新建文件 >> {}", path);
		log.info("Gitee API >> 仓库 >> 新建文件 >> {}", params);
		try {
			ResponseEntity<String> result = this.restTemplate.postForEntity(path, new HttpEntity<>(params, headers),
					String.class);
			log.info("Gitee API >> 仓库 >> 新建文件 >> {}", result.getBody());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	private boolean validated(GitConfig gitConfig) {
		if (!StringUtils.hasText(gitConfig.getAccessToken())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户授权码"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getCreateNewFileUrl())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置新建文件接口地址"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getDatePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置日期格式"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getFilePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置资源目录"));
			return false;
		}
		return true;
	}
}
