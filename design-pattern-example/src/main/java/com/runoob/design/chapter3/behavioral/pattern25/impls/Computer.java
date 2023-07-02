package com.runoob.design.chapter3.behavioral.pattern25.impls;

import com.runoob.design.chapter3.behavioral.pattern25.ComputerPart;
import com.runoob.design.chapter3.behavioral.pattern25.ComputerPartVisitor;

import java.util.Arrays;

/**
 * 电脑
 */
public class Computer implements ComputerPart {
	ComputerPart[] parts;

	public Computer() {
		parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };
	}

	@Override
	public void accept(ComputerPartVisitor computerPartVisitor) {
		Arrays.stream(parts).forEach(part -> {
			part.accept(computerPartVisitor);
		});
		computerPartVisitor.visit(this);
	}

}
