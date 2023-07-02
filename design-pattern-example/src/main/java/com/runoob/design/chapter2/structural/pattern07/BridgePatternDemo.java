package com.runoob.design.chapter2.structural.pattern07;

import com.runoob.design.chapter2.structural.pattern07.impls.GreenCircle;
import com.runoob.design.chapter2.structural.pattern07.impls.RedCircle;

/**
 * 桥接（Bridge）
 */
public class BridgePatternDemo {
	/**
	 * 使用 Shape 和 DrawAPI 类画出不同颜色的圆。
	 */
	public static void main(String[] args) {
		Shape redCircle = new Circle(100, 100, 10, new RedCircle());
		Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

		redCircle.draw();
		greenCircle.draw();
	}
}
