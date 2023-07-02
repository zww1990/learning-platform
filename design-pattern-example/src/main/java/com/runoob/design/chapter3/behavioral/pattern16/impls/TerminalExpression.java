package com.runoob.design.chapter3.behavioral.pattern16.impls;

import com.runoob.design.chapter3.behavioral.pattern16.Expression;

/**
 * 终止表达式
 */
public class TerminalExpression implements Expression {
	private String data;

	public TerminalExpression(String data) {
		this.data = data;
	}

	@Override
	public boolean interpret(String context) {
		return context.contains(data);
	}

}
