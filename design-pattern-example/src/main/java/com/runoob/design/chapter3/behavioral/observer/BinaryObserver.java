package com.runoob.design.chapter3.behavioral.observer;

public class BinaryObserver extends Observer {
	public BinaryObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		System.out.println("Binary String: "
				+ Integer.toBinaryString(subject.getState()));
	}

}
