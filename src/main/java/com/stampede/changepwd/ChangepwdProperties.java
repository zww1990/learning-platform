package com.stampede.changepwd;

import java.util.ArrayList;
import java.util.List;
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
	private PersonJob job = new PersonJob();

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

	public PersonJob getJob() {
		return job;
	}

	public void setJob(PersonJob job) {
		this.job = job;
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
		/** 时间格式 */
		private String pattern;
		/** 定时任务执行时间段 */
		private List<Time> times = new ArrayList<>();

		public String getCron() {
			return cron;
		}

		public String getPattern() {
			return pattern;
		}

		public List<Time> getTimes() {
			return times;
		}

		public void setCron(String cron) {
			this.cron = cron;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

		public void setTimes(List<Time> times) {
			this.times = times;
		}

		public static class Time {
			/** 开始时间 */
			private String begin;
			/** 结束时间 */
			private String end;

			public String getBegin() {
				return begin;
			}

			public String getEnd() {
				return end;
			}

			public void setBegin(String begin) {
				this.begin = begin;
			}

			public void setEnd(String end) {
				this.end = end;
			}
		}
	}
}
