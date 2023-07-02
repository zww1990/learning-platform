package com.runoob.design.chapter3.behavioral.pattern24;

/**
 * 创建一个抽象类，它的模板方法被设置为 final。
 */
public abstract class Game {
	public abstract void initialize();

	public abstract void startPlay();

	public abstract void endPlay();

	// 模板
	public final void play() {

		// 初始化游戏
		initialize();

		// 开始游戏
		startPlay();

		// 结束游戏
		endPlay();
	}
}
