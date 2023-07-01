package com.runoob.design.pattern1.creational.abstractfactory;

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
