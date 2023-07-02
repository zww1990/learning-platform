package com.runoob.design.chapter3.behavioral.pattern25;

import com.runoob.design.chapter3.behavioral.pattern25.impls.Computer;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Keyboard;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Monitor;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Mouse;

/**
 * 创建实现了上述类的实体访问者。
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

	@Override
	public void visit(Computer computer) {
		System.out.println("Displaying Computer.");
	}

	@Override
	public void visit(Mouse mouse) {
		System.out.println("Displaying Mouse.");
	}

	@Override
	public void visit(Keyboard keyboard) {
		System.out.println("Displaying Keyboard.");
	}

	@Override
	public void visit(Monitor monitor) {
		System.out.println("Displaying Monitor.");
	}

}
