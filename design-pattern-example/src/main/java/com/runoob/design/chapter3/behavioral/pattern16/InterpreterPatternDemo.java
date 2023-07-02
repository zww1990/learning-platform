package com.runoob.design.chapter3.behavioral.pattern16;

import com.runoob.design.chapter3.behavioral.pattern16.impls.AndExpression;
import com.runoob.design.chapter3.behavioral.pattern16.impls.OrExpression;
import com.runoob.design.chapter3.behavioral.pattern16.impls.TerminalExpression;

/**
 * 解释器模式（Interpreter Pattern）
 */
public class InterpreterPatternDemo {
	// 规则：Robert 和 John 是男性
	public static Expression getMaleExpression() {
		Expression robert = new TerminalExpression("Robert");
		Expression john = new TerminalExpression("John");
		return new OrExpression(robert, john);
	}

	// 规则：Julie 是一个已婚的女性
	public static Expression getMarriedWomanExpression() {
		Expression julie = new TerminalExpression("Julie");
		Expression married = new TerminalExpression("Married");
		return new AndExpression(julie, married);
	}

	/**
	 * InterpreterPatternDemo 使用 Expression 类来创建规则，并解析它们。
	 */
	public static void main(String[] args) {
		Expression isMale = getMaleExpression();
		Expression isMarriedWoman = getMarriedWomanExpression();

		System.out.println("John is male? " + isMale.interpret("John"));
		System.out.println("Julie is a married women? "
				+ isMarriedWoman.interpret("Married Julie"));
	}
}
