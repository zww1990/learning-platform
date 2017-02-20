package com.runoob.Design.Visitor;

public interface ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor);
}
