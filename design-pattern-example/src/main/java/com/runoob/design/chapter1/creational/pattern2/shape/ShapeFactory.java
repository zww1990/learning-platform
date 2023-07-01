package com.runoob.design.chapter1.creational.pattern2.shape;

import com.runoob.design.chapter1.creational.pattern2.AbstractFactory;
import com.runoob.design.chapter1.creational.pattern2.color.Color;
import com.runoob.design.chapter1.creational.pattern2.shape.impls.Circle;
import com.runoob.design.chapter1.creational.pattern2.shape.impls.Rectangle;
import com.runoob.design.chapter1.creational.pattern2.shape.impls.Square;

/**
 * 形状工厂
 */
public class ShapeFactory extends AbstractFactory {

	@Override
	public Color getColor(String color) {
		return null;
	}

	@Override
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
