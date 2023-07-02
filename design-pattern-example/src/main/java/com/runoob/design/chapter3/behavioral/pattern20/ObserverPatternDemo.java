package com.runoob.design.chapter3.behavioral.pattern20;

import com.runoob.design.chapter3.behavioral.pattern20.observer.BinaryObserver;
import com.runoob.design.chapter3.behavioral.pattern20.observer.HexaObserver;
import com.runoob.design.chapter3.behavioral.pattern20.observer.OctalObserver;

/**
 * 观察者模式（Observer Pattern）
 */
public class ObserverPatternDemo {
	/**
	 * 使用 Subject 和实体观察者对象。
	 */
	public static void main(String[] args) {
		Subject subject = new Subject();

		new HexaObserver(subject);
		new OctalObserver(subject);
		new BinaryObserver(subject);

		System.out.println("First state change: 15");
		subject.setState(15);
		System.out.println("Second state change: 10");
		subject.setState(10);
	}
}
