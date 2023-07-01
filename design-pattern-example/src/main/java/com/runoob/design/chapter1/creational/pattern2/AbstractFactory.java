package com.runoob.design.chapter1.creational.pattern2;

import com.runoob.design.chapter1.creational.pattern2.color.Color;
import com.runoob.design.chapter1.creational.pattern2.shape.Shape;

/**
 * 为 Color 和 Shape 对象创建抽象类来获取工厂。
 */
public abstract class AbstractFactory {
	/**
	 * 获取颜色
	 */
	public abstract Color getColor(String color);

	/**
	 * 获取形状
	 */
	public abstract Shape getShape(String shape);
}
