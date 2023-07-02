package com.runoob.design.chapter2.structural.pattern10;

import com.runoob.design.chapter2.structural.pattern10.impls.Circle;
import com.runoob.design.chapter2.structural.pattern10.impls.Rectangle;

/**
 * 装饰器模式（Decorator Pattern）
 */
public class DecoratorPatternDemo {
	/**
	 * 使用 RedShapeDecorator 来装饰 Shape 对象。
	 */
	public static void main(String[] args) {

		Shape circle = new Circle();

		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}
