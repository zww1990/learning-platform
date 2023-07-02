package com.runoob.design.chapter3.behavioral.pattern21.impls;

import com.runoob.design.chapter3.behavioral.pattern21.Context;
import com.runoob.design.chapter3.behavioral.pattern21.State;

/**
 * 停止状态
 */
public class StopState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("Player is in stop state");
		context.setState(this);
	}

	@Override
	public String toString() {
		return "Stop State";
	}
}
