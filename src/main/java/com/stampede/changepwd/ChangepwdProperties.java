package com.stampede.changepwd;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午5:59:56
 * @description 属性配置类
 */
@ConfigurationProperties(prefix = "changepwd")
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

	public String getGitlabUrl() {
		return gitlabUrl;
	}

	public void setGitlabUrl(String gitlabUrl) {
		this.gitlabUrl = gitlabUrl;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public FromSubjectText getCreate() {
		return create;
	}

	public FromSubjectText getReset() {
		return reset;
	}

	public FromSubjectText getUpdate() {
		return update;
	}

	public void setCreate(FromSubjectText create) {
		this.create = create;
	}

	public void setReset(FromSubjectText reset) {
		this.reset = reset;
	}

	public void setUpdate(FromSubjectText update) {
		this.update = update;
	}

	public PersonJob getFirst() {
		return first;
	}

	public PersonJob getSecond() {
		return second;
	}

	public PersonJob getThird() {
		return third;
	}

	public void setFirst(PersonJob first) {
		this.first = first;
	}

	public void setSecond(PersonJob second) {
		this.second = second;
	}

	public void setThird(PersonJob third) {
		this.third = third;
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月26日,下午7:27:31
	 * @description 发件人、主题、正文
	 */
	public static class FromSubjectText {
		/** 有效期 */
		private int expiration;
		/** 发件人 */
		private String from;
		/** 主题 */
		private String subject;
		/** 正文 */
		private String text;

		public int getExpiration() {
			return expiration;
		}

		public String getFrom() {
			return from;
		}

		public String getSubject() {
			return subject;
		}

		public String getText() {
			return text;
		}

		public void setExpiration(int expiration) {
			this.expiration = expiration;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public void setText(String text) {
			this.text = text;
		}
	}

	public static class PersonJob {
		/** 定时任务时间表达式 */
		private String cron;
		/** 开始时间 */
		private String begin;
		/** 结束时间 */
		private String end;

		public String getCron() {
			return cron;
		}

		public String getBegin() {
			return begin;
		}

		public String getEnd() {
			return end;
		}

		public void setCron(String cron) {
			this.cron = cron;
		}

		public void setBegin(String begin) {
			this.begin = begin;
		}

		public void setEnd(String end) {
			this.end = end;
		}
	}
}
