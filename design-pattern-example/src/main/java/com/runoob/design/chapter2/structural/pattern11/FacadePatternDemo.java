package com.runoob.design.chapter2.structural.pattern11;

/**
 * 外观模式（Facade Pattern）
 */
public class FacadePatternDemo {
	/**
	 * 使用该外观类画出各种类型的形状。
	 */
	public static void main(String[] args) {
		ShapeMaker shapeMaker = new ShapeMaker();

		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
	}
}
