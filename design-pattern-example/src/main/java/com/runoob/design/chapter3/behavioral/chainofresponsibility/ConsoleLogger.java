package com.runoob.design.chapter3.behavioral.chainofresponsibility;

public class ConsoleLogger extends AbstractLogger {
	public ConsoleLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}

}
