package com.runoob.design.chapter2.structural.pattern10;

/**
 * 创建扩展了 ShapeDecorator 类的实体装饰类。
 */
public class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		decoratedShape.draw();
		setRedBorder(decoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}

}
