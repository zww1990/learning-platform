package com.stampede.changepwd;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午5:59:56
 * @description 属性配置类
 */
@ConfigurationProperties(prefix = "changepwd")
public class ChangepwdProperties {
	private FromSubjectText create;
	private FromSubjectText reset;
	private FromSubjectText update;

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

	public static class FromSubjectText {
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
}
