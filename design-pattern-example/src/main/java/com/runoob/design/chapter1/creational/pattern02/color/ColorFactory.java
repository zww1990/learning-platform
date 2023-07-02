package com.runoob.design.chapter1.creational.pattern02.color;

import com.runoob.design.chapter1.creational.pattern02.AbstractFactory;
import com.runoob.design.chapter1.creational.pattern02.color.impls.Blue;
import com.runoob.design.chapter1.creational.pattern02.color.impls.Green;
import com.runoob.design.chapter1.creational.pattern02.color.impls.Red;
import com.runoob.design.chapter1.creational.pattern02.shape.Shape;

/**
 * 颜色工厂
 */
public class ColorFactory extends AbstractFactory {

	@Override
	public Color getColor(String color) {
		if (color != null) {
			if (color.equalsIgnoreCase("RED")) {
				return new Red();
			} else if (color.equalsIgnoreCase("GREEN")) {
				return new Green();
			} else if (color.equalsIgnoreCase("BLUE")) {
				return new Blue();
			}
		}
		return null;
	}

	@Override
	public Shape getShape(String shape) {
		return null;
	}

}
