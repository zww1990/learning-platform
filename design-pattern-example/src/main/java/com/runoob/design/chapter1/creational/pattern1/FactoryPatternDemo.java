package com.runoob.design.chapter1.creational.pattern1;

/**
 * 工厂模式（Factory Pattern）
 */
public class FactoryPatternDemo {
	/**
	 * 我们将创建一个 Shape 接口和实现 Shape 接口的实体类。<br>
	 * 下一步是定义工厂类 ShapeFactory。<br>
	 * FactoryPatternDemo 类使用 ShapeFactory 来获取 Shape 对象。<br>
	 * 它将向 ShapeFactory 传递信息（CIRCLE / RECTANGLE / SQUARE），以便获取它所需对象的类型。<br>
	 */
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();

		// 获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape("CIRCLE");

		// 调用 Circle 的 draw 方法
		shape1.draw();

		// 获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape("RECTANGLE");

		// 调用 Rectangle 的 draw 方法
		shape2.draw();

		// 获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = shapeFactory.getShape("SQUARE");

		// 调用 Square 的 draw 方法
		shape3.draw();
	}
}
