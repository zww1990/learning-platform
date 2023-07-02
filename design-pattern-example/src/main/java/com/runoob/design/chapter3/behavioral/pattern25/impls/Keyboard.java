package com.runoob.design.chapter3.behavioral.pattern25.impls;

import com.runoob.design.chapter3.behavioral.pattern25.ComputerPart;
import com.runoob.design.chapter3.behavioral.pattern25.ComputerPartVisitor;

/**
 * 键盘
 */
public class Keyboard implements ComputerPart {

	@Override
	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}

}
