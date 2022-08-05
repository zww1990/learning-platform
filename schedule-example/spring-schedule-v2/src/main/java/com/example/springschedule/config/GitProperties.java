package com.example.springschedule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Git属性配置
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:57:30
 */
@ConfigurationProperties(prefix = "git")
@Configuration
@Getter
@Setter
@ToString
public class GitProperties {
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
}
