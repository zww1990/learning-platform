package com.stampede.changepwd.constant;

import java.util.Random;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:29:18
 * @description 常量类
 */
public abstract class Constants {
	/** 背景图片名 */
	private static final String[] IMAGES = { "clouds", "sky", "space", "stars" };

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:28:52
	 * @return 随机抽选背景图片名
	 */
	public static final String randomImage() {
		return IMAGES[new Random().nextInt(IMAGES.length)];
	}
}
