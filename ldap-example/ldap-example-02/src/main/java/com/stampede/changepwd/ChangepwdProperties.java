package com.stampede.changepwd;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午5:59:56
 * @description 属性配置类
 */
@ConfigurationProperties(prefix = "changepwd")
@Getter
@Setter
@ToString
public class ChangepwdProperties {
	/** 创建密码 */
	@NestedConfigurationProperty
	private FromSubjectText create = new FromSubjectText();
	/** 重置密码 */
	@NestedConfigurationProperty
	private FromSubjectText reset = new FromSubjectText();
	/** 修改密码 */
	@NestedConfigurationProperty
	private FromSubjectText update = new FromSubjectText();
	@NestedConfigurationProperty
	private PersonJob first = new PersonJob();
	@NestedConfigurationProperty
	private PersonJob second = new PersonJob();
	@NestedConfigurationProperty
	private PersonJob third = new PersonJob();
	/**
	 * 初始化默认密码
	 */
	private String defaultPassword = "[none]";
	/**
	 * gitlab地址
	 */
	private String gitlabUrl = "http://gitlab.it.5i5j.com/";
	/**
	 * jira地址
	 */
	private String jiraUrl = "http://jira.bacic5i5j.com/";
	/**
	 * jira管理员用户
	 */
	private String jiraAdminUsername = "siqianwen";
	/**
	 * jira管理员密码
	 */
	private String jiraAdminPassword = "ainilaoma1234A";
	/**
	 * jira用户目录同步地址
	 */
	private String jiraSyncUrl = this.jiraUrl + "rest/crowd/1/directory/10000";

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月26日,下午7:27:31
	 * @description 发件人、主题、正文
	 */
	@Getter
	@Setter
	@ToString
	public static class FromSubjectText {
		/** 有效期 */
		private int expiration;
		/** 发件人 */
		private String from;
		/** 主题 */
		private String subject;
		/** 正文 */
		private String text;

	}

	@Getter
	@Setter
	@ToString
	public static class PersonJob {
		/** 定时任务时间表达式 */
		private String cron;
		/** 开始时间 */
		private String begin;
		/** 结束时间 */
		private String end;

	}
}
