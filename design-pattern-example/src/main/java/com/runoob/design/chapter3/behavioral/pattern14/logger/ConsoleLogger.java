package com.runoob.design.chapter3.behavioral.pattern14.logger;

import com.runoob.design.chapter3.behavioral.pattern14.AbstractLogger;

public class ConsoleLogger extends AbstractLogger {
	public ConsoleLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}

}
