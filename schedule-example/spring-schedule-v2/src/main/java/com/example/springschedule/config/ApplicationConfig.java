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
@ConfigurationProperties(prefix = ApplicationConfig.APP_CONFIG_PREFIX)
@Getter
@Setter
@ToString
public class ApplicationConfig {
	public static final String APP_CONFIG_PREFIX = "app";
	/** Jgit任务属性配置 */
	private JobConfig jgitJob = new JobConfig();
	/** Gitee任务属性配置 */
	private JobConfig giteeJob = new JobConfig();
	/** Github任务属性配置 */
	private JobConfig githubJob = new JobConfig();
	/** Jgit属性配置 */
	private JgitConfig jgitConfig = new JgitConfig();
	/** Gitee属性配置 */
	private GiteeConfig giteeConfig = new GiteeConfig();
	/** Github属性配置 */
	private GithubConfig githubConfig = new GithubConfig();

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
		/** 是否启用 */
		private Boolean enabled;
	}

	@Getter
	@Setter
	@ToString
	public static class JgitConfig {
		/** 远程仓库地址 */
		private String remoteUrl;
		/** 本地克隆目录 */
		private String localDirectory;
		/** 作者 */
		private String author;
		/** 用户名 */
		private String username;
		/** 密码 */
		private String password;
		/** 分支名 */
		private String branchName = "master";
		/** 日期格式 */
		private String datePattern = "yyyyMMdd";
		/** 文件的路径格式 */
		private String pathFormat;
		/** 文件的内容格式 */
		private String contentFormat;
	}

	@Getter
	@Setter
	@ToString
	public static class GiteeConfig {
		/** 用户授权码 */
		private String accessToken;
		/** 仓库所属空间地址 */
		private String owner;
		/** 仓库路径 */
		private String repo;
		/** 文件的路径格式 */
		private String pathFormat;
		/** 文件的内容格式 */
		private String contentFormat;
		/** 日期格式 */
		private String datePattern = "yyyyMMdd";
	}

	@Getter
	@Setter
	@ToString
	public static class GithubConfig {
		/** 用户授权码 */
		private String accessToken;
		/** 存储库名称 */
		private String repositoryName;
		/** 文件的路径格式 */
		private String pathFormat;
		/** 文件的内容格式 */
		private String contentFormat;
		/** 日期格式 */
		private String datePattern = "yyyyMMdd";
	}
}
