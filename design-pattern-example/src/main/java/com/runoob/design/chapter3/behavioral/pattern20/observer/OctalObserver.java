package com.runoob.design.chapter3.behavioral.pattern20.observer;

import com.runoob.design.chapter3.behavioral.pattern20.Observer;
import com.runoob.design.chapter3.behavioral.pattern20.Subject;

public class OctalObserver extends Observer {
	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		System.out.println("Octal String: "
				+ Integer.toOctalString(subject.getState()));
	}

}
