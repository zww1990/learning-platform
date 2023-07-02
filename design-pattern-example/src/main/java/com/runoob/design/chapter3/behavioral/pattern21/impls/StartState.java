package com.runoob.design.chapter3.behavioral.pattern21.impls;

import com.runoob.design.chapter3.behavioral.pattern21.Context;
import com.runoob.design.chapter3.behavioral.pattern21.State;

/**
 * 开始状态
 */
public class StartState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("Player is in start state");
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Start State";
	}
}
