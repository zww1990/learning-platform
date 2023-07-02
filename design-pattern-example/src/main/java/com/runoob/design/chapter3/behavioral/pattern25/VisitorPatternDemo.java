package com.runoob.design.chapter3.behavioral.pattern25;

import com.runoob.design.chapter3.behavioral.pattern25.impls.Computer;

/**
 * 访问者模式（Visitor Pattern）
 */
public class VisitorPatternDemo {
	/**
	 * 使用 ComputerPartDisplayVisitor 来显示 Computer 的组成部分。
	 */
	public static void main(String[] args) {

		ComputerPart computer = new Computer();
		computer.accept(new ComputerPartDisplayVisitor());
	}
}
