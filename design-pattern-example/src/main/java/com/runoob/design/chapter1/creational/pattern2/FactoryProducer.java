package com.runoob.design.chapter1.creational.pattern2;

import com.runoob.design.chapter1.creational.pattern2.color.ColorFactory;
import com.runoob.design.chapter1.creational.pattern2.shape.ShapeFactory;

/**
 * 创建一个工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂。
 */
public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {
		if (choice != null) {
			if (choice.equalsIgnoreCase("SHAPE")) {
				return new ShapeFactory();
			} else if (choice.equalsIgnoreCase("COLOR")) {
				return new ColorFactory();
			}
		}
		return null;
	}
}
