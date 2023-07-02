package com.runoob.design.chapter3.behavioral.pattern14.logger;

import com.runoob.design.chapter3.behavioral.pattern14.AbstractLogger;

public class FileLogger extends AbstractLogger {
	public FileLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		System.out.println("File::Logger: " + message);
	}

}
