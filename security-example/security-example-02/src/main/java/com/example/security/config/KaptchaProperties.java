package com.example.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.google.code.kaptcha.impl.DefaultBackground;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.DefaultNoise;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 验证码配置属性类
 * 
 * @author home
 */
@ConfigurationProperties(prefix = "kaptcha")
@Getter
@Setter
@ToString
public class KaptchaProperties {
	@NestedConfigurationProperty
	private Border border = new Border();
	@NestedConfigurationProperty
	private Noise noise = new Noise();
	@NestedConfigurationProperty
	private Obscurificator obscurificator = new Obscurificator();
	@NestedConfigurationProperty
	private Producer producer = new Producer();
	@NestedConfigurationProperty
	private Textproducer textproducer = new Textproducer();
	@NestedConfigurationProperty
	private Word word = new Word();
	@NestedConfigurationProperty
	private Background background = new Background();
	@NestedConfigurationProperty
	private Image image = new Image();

	@Getter
	@Setter
	@ToString
	public static class Border {
		public enum Enabled {
			/** 启用 */
			yes,
			/** 禁用 */
			no
		}

		/** 是否启用边框 */
		private Enabled enabled = Enabled.yes;
		/** 边框颜色 */
		private String color = "BLACK";
		/** 边框宽度 */
		private String thickness = "1";

	}

	@Getter
	@Setter
	@ToString
	public static class Noise {
		/** 干扰颜色 */
		private String color = "BLACK";
		/** 干扰实现类 */
		private String impl = DefaultNoise.class.getName();

	}

	@Getter
	@Setter
	@ToString
	public static class Obscurificator {
		/** 图片样式实现类 */
		private String impl = WaterRipple.class.getName();

	}

	@Getter
	@Setter
	@ToString
	public static class Producer {
		/** 图片实现类 */
		private String impl = DefaultKaptcha.class.getName();

	}

	@Getter
	@Setter
	@ToString
	public static class Textproducer {
		/** 文本实现类 */
		private String impl = DefaultTextCreator.class.getName();
		/** 文本集合，验证码值从此集合中获取 */
		private String charString = "abcde2345678gfynmnpwx";
		/** 验证码长度 */
		private String charLength = "5";
		/** 文字间隔 */
		private String charSpace = "2";
		/** 字体 */
		@NestedConfigurationProperty
		private Font font = new Font();

		@Getter
		@Setter
		@ToString
		public static class Font {
			/** 字体名称 */
			private String names = "Arial,Courier";
			/** 字体颜色 */
			private String color = "BLACK";
			/** 字体大小 */
			private String size = "40";

		}
	}

	@Getter
	@Setter
	@ToString
	public static class Word {
		/** 文字渲染器实现类 */
		private String impl = DefaultWordRenderer.class.getName();

	}

	@Getter
	@Setter
	@ToString
	public static class Background {
		/** 背景实现类 */
		private String impl = DefaultBackground.class.getName();
		/** 背景颜色渐变 */
		@NestedConfigurationProperty
		private Clear clear = new Clear();

		@Getter
		@Setter
		@ToString
		public static class Clear {
			/** 背景颜色渐变，开始颜色 */
			private String from = "LIGHT_GRAY";
			/** 背景颜色渐变， 结束颜色 */
			private String to = "WHITE";

		}
	}

	@Getter
	@Setter
	@ToString
	public static class Image {
		/** 图片宽 */
		private String width = "200";
		/** 图片高 */
		private String height = "50";

	}
}
