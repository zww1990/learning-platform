package com.runoob.design.chapter3.behavioral.pattern25;

import com.runoob.design.chapter3.behavioral.pattern25.impls.Computer;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Keyboard;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Monitor;
import com.runoob.design.chapter3.behavioral.pattern25.impls.Mouse;

/**
 * 定义一个表示访问者的接口。
 */
public interface ComputerPartVisitor {
	void visit(Computer computer);

	void visit(Mouse mouse);

	void visit(Keyboard keyboard);

	void visit(Monitor monitor);
}
