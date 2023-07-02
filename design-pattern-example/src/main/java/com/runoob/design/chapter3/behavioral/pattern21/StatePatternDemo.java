package com.runoob.design.chapter3.behavioral.pattern21;

import com.runoob.design.chapter3.behavioral.pattern21.impls.StartState;
import com.runoob.design.chapter3.behavioral.pattern21.impls.StopState;

/**
 * 状态模式（State Pattern）
 */
public class StatePatternDemo {
	/**
	 * 使用 Context 来查看当状态 State 改变时的行为变化。
	 */
	public static void main(String[] args) {
		Context context = new Context();

		StartState startState = new StartState();
		startState.doAction(context);

		System.out.println(context.getState().toString());

		StopState stopState = new StopState();
		stopState.doAction(context);

		System.out.println(context.getState().toString());
	}
}
