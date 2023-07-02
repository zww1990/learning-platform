package com.runoob.design.chapter3.behavioral.pattern23;

import com.runoob.design.chapter3.behavioral.pattern23.impls.OperationAdd;
import com.runoob.design.chapter3.behavioral.pattern23.impls.OperationMultiply;
import com.runoob.design.chapter3.behavioral.pattern23.impls.OperationSubstract;

/**
 * 策略模式（Strategy Pattern）
 */
public class StrategyPatternDemo {
	/**
	 * 使用 Context 来查看当它改变策略 Strategy 时的行为变化。
	 */
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubstract());
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiply());
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
	}
}
