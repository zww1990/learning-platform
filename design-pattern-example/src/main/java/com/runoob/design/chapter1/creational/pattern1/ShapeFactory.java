package com.runoob.design.chapter1.creational.pattern1;

import com.runoob.design.chapter1.creational.pattern1.impls.Square;
import com.runoob.design.chapter1.creational.pattern1.impls.Circle;
import com.runoob.design.chapter1.creational.pattern1.impls.Rectangle;

/**
 * 创建一个工厂，生成基于给定信息的实体类的对象。
 */
public class ShapeFactory {
	// 使用 getShape 方法获取形状类型的对象
	public Shape getShape(String shapeType) {
		if (shapeType != null) {
			if (shapeType.equalsIgnoreCase("CIRCLE")) {
				return new Circle();
			} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
				return new Rectangle();
			} else if (shapeType.equalsIgnoreCase("SQUARE")) {
				return new Square();
			}
		}
		return null;
	}
}
