package com.example.springschedule.service.job;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import com.example.springschedule.config.ApplicationConfig;
import com.example.springschedule.config.ApplicationConfig.GitConfig;
import com.example.springschedule.service.exchange.ContentResponse;
import com.example.springschedule.service.exchange.CreateNewFileRequest;
import com.example.springschedule.service.exchange.GiteeHttpExchange;
import com.example.springschedule.service.exchange.UpdateFileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired
	private ApplicationConfig appConfig;
	@Autowired
	private GiteeHttpExchange giteeHttpExchange;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GitConfig gc = this.appConfig.getGitConfig();
		if (!this.validated(gc)) {
			return;
		}
		// 文件的路径
		String path = String.format(gc.getPathFormat(),
				LocalDate.now().format(DateTimeFormatter.ofPattern(gc.getDatePattern())));
		ContentResponse response = this.giteeHttpExchange.defaultGetContent(gc.getOwner(), gc.getRepo(), path,
				gc.getAccessToken(), this.objectMapper);
		// 提交信息
		String message = String.format("hello world %s", System.currentTimeMillis());
		// 文件内容, 要用 base64 编码
		String content = Base64Utils.encodeToString(message.getBytes(StandardCharsets.UTF_8));
		if (response.getSize() == 0) {
			log.info("Gitee API >> 仓库 >> 新建文件 >> {}", path);
			log.info("Gitee API >> 仓库 >> 新建文件 >> {}", message);
			log.info("Gitee API >> 仓库 >> 新建文件 >> {}", content);
			try {
				String result = this.giteeHttpExchange.createNewFile(gc.getOwner(), gc.getRepo(), path,
						new CreateNewFileRequest(gc.getAccessToken(), content, message));
				log.info("Gitee API >> 仓库 >> 新建文件 >> {}", result);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(), e);
			}
		} else {
			log.info("Gitee API >> 仓库 >> 更新文件 >> {}", path);
			log.info("Gitee API >> 仓库 >> 更新文件 >> {}", message);
			log.info("Gitee API >> 仓库 >> 更新文件 >> {}", content);
			try {
				String result = this.giteeHttpExchange.updateFile(gc.getOwner(), gc.getRepo(), path,
						new UpdateFileRequest(gc.getAccessToken(), content, message, response.getSha()));
				log.info("Gitee API >> 仓库 >> 更新文件 >> {}", result);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	private boolean validated(GitConfig gitConfig) {
		if (!StringUtils.hasText(gitConfig.getAccessToken())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户授权码"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getOwner())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置仓库所属空间地址"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getRepo())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置仓库路径"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getPathFormat())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置文件的路径格式"));
			return false;
		}
		return true;
	}
}
