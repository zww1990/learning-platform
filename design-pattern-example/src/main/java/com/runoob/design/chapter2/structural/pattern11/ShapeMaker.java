package com.runoob.design.chapter2.structural.pattern11;

import com.runoob.design.chapter2.structural.pattern11.impls.Circle;
import com.runoob.design.chapter2.structural.pattern11.impls.Rectangle;
import com.runoob.design.chapter2.structural.pattern11.impls.Square;

/**
 * 创建一个外观类。
 */
public class ShapeMaker {
	private Shape circle;
	private Shape rectangle;
	private Shape square;

	public ShapeMaker() {
		circle = new Circle();
		rectangle = new Rectangle();
		square = new Square();
	}

	public void drawCircle() {
		circle.draw();
	}

	public void drawRectangle() {
		rectangle.draw();
	}

	public void drawSquare() {
		square.draw();
	}
}
