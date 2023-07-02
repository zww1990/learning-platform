package com.runoob.design.chapter3.behavioral.pattern25;

/**
 * 电脑组件
 */
public interface ComputerPart {
	void accept(ComputerPartVisitor computerPartVisitor);
}
