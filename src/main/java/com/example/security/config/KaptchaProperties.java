package com.example.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.google.code.kaptcha.impl.DefaultBackground;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.impl.DefaultNoise;
import com.google.code.kaptcha.impl.WaterRipple;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.text.impl.DefaultWordRenderer;

/**
 * 验证码配置属性类
 * 
 * @author home
 */
@ConfigurationProperties(prefix = "kaptcha")
public class KaptchaProperties {
	private Border border = new Border();
	private Noise noise = new Noise();
	private Obscurificator obscurificator = new Obscurificator();
	private Producer producer = new Producer();
	private Textproducer textproducer = new Textproducer();
	private Word word = new Word();
	private Background background = new Background();
	private Image image = new Image();

	public Border getBorder() {
		return border;
	}

	public Noise getNoise() {
		return noise;
	}

	public Obscurificator getObscurificator() {
		return obscurificator;
	}

	public Producer getProducer() {
		return producer;
	}

	public Textproducer getTextproducer() {
		return textproducer;
	}

	public Word getWord() {
		return word;
	}

	public Background getBackground() {
		return background;
	}

	public Image getImage() {
		return image;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

	public void setNoise(Noise noise) {
		this.noise = noise;
	}

	public void setObscurificator(Obscurificator obscurificator) {
		this.obscurificator = obscurificator;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public void setTextproducer(Textproducer textproducer) {
		this.textproducer = textproducer;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public void setImage(Image image) {
		this.image = image;
	}

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

		public Enabled getEnabled() {
			return enabled;
		}

		public String getColor() {
			return color;
		}

		public String getThickness() {
			return thickness;
		}

		public void setEnabled(Enabled enabled) {
			this.enabled = enabled;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public void setThickness(String thickness) {
			this.thickness = thickness;
		}
	}

	public static class Noise {
		/** 干扰颜色 */
		private String color = "BLACK";
		/** 干扰实现类 */
		private String impl = DefaultNoise.class.getName();

		public String getColor() {
			return color;
		}

		public String getImpl() {
			return impl;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}
	}

	public static class Obscurificator {
		/** 图片样式实现类 */
		private String impl = WaterRipple.class.getName();

		public String getImpl() {
			return impl;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}
	}

	public static class Producer {
		/** 图片实现类 */
		private String impl = DefaultKaptcha.class.getName();

		public String getImpl() {
			return impl;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}
	}

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
		private Font font = new Font();

		public String getImpl() {
			return impl;
		}

		public String getCharString() {
			return charString;
		}

		public String getCharLength() {
			return charLength;
		}

		public String getCharSpace() {
			return charSpace;
		}

		public Font getFont() {
			return font;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}

		public void setCharString(String charString) {
			this.charString = charString;
		}

		public void setCharLength(String charLength) {
			this.charLength = charLength;
		}

		public void setCharSpace(String charSpace) {
			this.charSpace = charSpace;
		}

		public void setFont(Font font) {
			this.font = font;
		}

		public static class Font {
			/** 字体名称 */
			private String names = "Arial,Courier";
			/** 字体颜色 */
			private String color = "BLACK";
			/** 字体大小 */
			private String size = "40";

			public String getNames() {
				return names;
			}

			public String getColor() {
				return color;
			}

			public String getSize() {
				return size;
			}

			public void setNames(String names) {
				this.names = names;
			}

			public void setColor(String color) {
				this.color = color;
			}

			public void setSize(String size) {
				this.size = size;
			}
		}
	}

	public static class Word {
		/** 文字渲染器实现类 */
		private String impl = DefaultWordRenderer.class.getName();

		public String getImpl() {
			return impl;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}
	}

	public static class Background {
		/** 背景实现类 */
		private String impl = DefaultBackground.class.getName();
		/** 背景颜色渐变 */
		private Clear clear = new Clear();

		public String getImpl() {
			return impl;
		}

		public Clear getClear() {
			return clear;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}

		public void setClear(Clear clear) {
			this.clear = clear;
		}

		public static class Clear {
			/** 背景颜色渐变，开始颜色 */
			private String from = "LIGHT_GRAY";
			/** 背景颜色渐变， 结束颜色 */
			private String to = "WHITE";

			public String getFrom() {
				return from;
			}

			public String getTo() {
				return to;
			}

			public void setFrom(String from) {
				this.from = from;
			}

			public void setTo(String to) {
				this.to = to;
			}
		}
	}

	public static class Image {
		/** 图片宽 */
		private String width = "200";
		/** 图片高 */
		private String height = "50";

		public String getWidth() {
			return width;
		}

		public String getHeight() {
			return height;
		}

		public void setWidth(String width) {
			this.width = width;
		}

		public void setHeight(String height) {
			this.height = height;
		}
	}
}
