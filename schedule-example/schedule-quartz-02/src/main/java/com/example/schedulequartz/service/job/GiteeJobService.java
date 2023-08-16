package com.example.schedulequartz.service.job;

import com.example.schedulequartz.config.ApplicationConfig;
import com.example.schedulequartz.config.ApplicationConfig.GiteeConfig;
import com.example.schedulequartz.service.exchange.ContentResponse;
import com.example.schedulequartz.service.exchange.CreateNewFileRequest;
import com.example.schedulequartz.service.exchange.GiteeHttpExchange;
import com.example.schedulequartz.service.exchange.UpdateFileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * Gitee Job Service
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午12:40:13
 */
@DisallowConcurrentExecution
@Slf4j
@AllArgsConstructor
public class GiteeJobService extends QuartzJobBean {
	private ApplicationConfig appConfig;
	private GiteeHttpExchange giteeHttpExchange;
	private ObjectMapper objectMapper;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GiteeConfig gc = this.appConfig.getGiteeConfig();
		if (!this.validated(gc)) {
			return;
		}
		LocalDateTime now = LocalDateTime.now().withNano(0);
		// 文件的路径
		String path = String.format(gc.getPathFormat(), DateTimeFormatter.ofPattern(gc.getDatePattern()).format(now));
		ContentResponse response = this.giteeHttpExchange.defaultGetContent(gc.getOwner(), gc.getRepo(), path,
				gc.getAccessToken(), this.objectMapper);
		// 提交信息
		String message = String.format(gc.getContentFormat(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
		// 文件内容, 要用 base64 编码
		String content = Base64.getUrlEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
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

	private boolean validated(GiteeConfig gc) {
		if (!StringUtils.hasText(gc.getAccessToken())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户授权码"));
			return false;
		}
		if (!StringUtils.hasText(gc.getOwner())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置仓库所属空间地址"));
			return false;
		}
		if (!StringUtils.hasText(gc.getRepo())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置仓库路径"));
			return false;
		}
		if (!StringUtils.hasText(gc.getPathFormat())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置文件的路径格式"));
			return false;
		}
		if (!StringUtils.hasText(gc.getDatePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置日期格式"));
			return false;
		}
		if (!StringUtils.hasText(gc.getContentFormat())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置文件的内容格式"));
			return false;
		}
		return true;
	}
}
