package com.runoob.design.chapter3.behavioral.pattern20.observer;

import com.runoob.design.chapter3.behavioral.pattern20.Observer;
import com.runoob.design.chapter3.behavioral.pattern20.Subject;

public class HexaObserver extends Observer {
	public HexaObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		System.out.println("Hex String: "
				+ Integer.toHexString(subject.getState()).toUpperCase());
	}

}
