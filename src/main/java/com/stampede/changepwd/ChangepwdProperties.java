package com.stampede.changepwd;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZhangWeiWei
 * @date 2020年2月26日,下午5:59:56
 * @description 属性配置类
 */
@ConfigurationProperties(prefix = "changepwd")
public class ChangepwdProperties {
	/** 创建密码 */
	private FromSubjectText create;
	/** 重置密码 */
	private FromSubjectText reset;
	/** 修改密码 */
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
}
