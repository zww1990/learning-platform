package com.stampede.changepwd;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午5:59:56
 * @description 属性配置类
 */
@ConfigurationProperties(prefix = "changepwd")
public class ChangepwdProperties {
	private Reset reset;
	private Update update;

	public Reset getReset() {
		return reset;
	}

	public Update getUpdate() {
		return update;
	}

	public void setReset(Reset reset) {
		this.reset = reset;
	}

	public void setUpdate(Update update) {
		this.update = update;
	}

	public static class Reset {
		private int expiration;
		private String from;
		private String subject;
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

	public static class Update {
		private String from;
		private String subject;
		private String text;

		public String getFrom() {
			return from;
		}

		public String getSubject() {
			return subject;
		}

		public String getText() {
			return text;
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
}
