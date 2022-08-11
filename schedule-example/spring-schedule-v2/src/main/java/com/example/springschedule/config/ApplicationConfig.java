package com.example.springschedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Spring应用程序属性配置
 * 
 * @author zhang weiwei
 * @since 2022年8月10日,下午8:44:15
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationConfig {
	/** 任务属性配置 */
	private JobConfig jobConfig = new JobConfig();
	/** Git属性配置 */
	private GitConfig gitConfig = new GitConfig();

	@Getter
	@Setter
	@ToString
	public static class JobConfig {
		/** 任务键 */
		private String jobKey;
		/** 触发器键 */
		private String triggerKey;
		/** cron表达式 */
		private String cronExpression;
	}

	@Getter
	@Setter
	@ToString
	public static class GitConfig {
		/** 远程仓库地址 */
		private String remoteUrl;
		/** 本地克隆目录 */
		private String localDirectory;
		/** 用户名 */
		private String username;
		/** 作者 */
		private String author;
		/** 密码 */
		private String password;
		/** 分支名 */
		private String branchName = "master";
		/** 日期格式 */
		private String datePattern = "yyyyMMdd";
		/** 资源目录 */
		private String filePattern;
		/** 新建文件接口地址 */
		private String createNewFileUrl;
		/** 用户授权码 */
		private String accessToken;
	}
}
