package com.runoob.design.chapter2.structural.pattern12;

/**
 * 享元模式（Flyweight Pattern）
 */
public class FlyweightPatternDemo {
	private static final String[] colors = { "Red", "Green", "Blue", "White", "Black" };

	/**
	 * 使用该工厂，通过传递颜色信息来获取实体类的对象。
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 20; ++i) {
			Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
	}

	private static String getRandomColor() {
		return colors[(int) (Math.random() * colors.length)];
	}

	private static int getRandomX() {
		return (int) (Math.random() * 100);
	}

	private static int getRandomY() {
		return (int) (Math.random() * 100);
	}
}
