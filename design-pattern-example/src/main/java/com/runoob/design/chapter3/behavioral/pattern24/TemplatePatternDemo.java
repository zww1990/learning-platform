package com.runoob.design.chapter3.behavioral.pattern24;

import com.runoob.design.chapter3.behavioral.pattern24.game.Cricket;
import com.runoob.design.chapter3.behavioral.pattern24.game.Football;

/**
 * 模板模式（Template Pattern）
 */
public class TemplatePatternDemo {
	/**
	 * 使用 Game 的模板方法 play() 来演示游戏的定义方式。
	 */
	public static void main(String[] args) {

		Game game = new Cricket();
		game.play();
		System.out.println();
		game = new Football();
		game.play();
	}
}
