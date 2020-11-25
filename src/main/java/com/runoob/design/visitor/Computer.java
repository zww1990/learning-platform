package com.runoob.design.visitor;

import java.util.Arrays;

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
