package com.example.springschedule.service.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.HttpException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import com.example.springschedule.config.ApplicationConfig;
import com.example.springschedule.config.ApplicationConfig.GithubConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * Github Job Service
 * 
 * @author zhang weiwei
 * @since 2022年12月11日,下午3:27:48
 */
@DisallowConcurrentExecution
@Slf4j
public class GithubJobService extends QuartzJobBean {
	@Autowired
	private ApplicationConfig appConfig;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GithubConfig gc = this.appConfig.getGithubConfig();
		if (!this.validated(gc)) {
			return;
		}
		try {
			GitHub connect = GitHub.connectUsingOAuth(gc.getAccessToken());
			log.info("正在使用oauth连接到GitHub");
			if (connect.isCredentialValid()) {
				log.info("凭据有效，认证[{}]成功。", connect.getApiUrl());
				GHRepository repository = connect.getRepository(gc.getRepositoryName());
				log.info("正在获取存储库[{}]", repository.getHttpTransportUrl());
				LocalDateTime now = LocalDateTime.now().withNano(0);
				String path = String.format(gc.getPathFormat(),
						DateTimeFormatter.ofPattern(gc.getDatePattern()).format(now));
				String content = String.format(gc.getContentFormat(),
						DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
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

	private boolean validated(GithubConfig gc) {
		if (!StringUtils.hasText(gc.getAccessToken())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户授权码"));
			return false;
		}
		if (!StringUtils.hasText(gc.getRepositoryName())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置存储库名称"));
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
