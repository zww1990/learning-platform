package com.example.springschedule.service;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
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

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GitConfig gitConfig = this.appConfig.getGitConfig();
		if (!this.validated(gitConfig)) {
			return;
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
		return true;
	}
}
