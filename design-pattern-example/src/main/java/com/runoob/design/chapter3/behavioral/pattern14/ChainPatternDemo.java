package com.runoob.design.chapter3.behavioral.pattern14;

import com.runoob.design.chapter3.behavioral.pattern14.logger.ConsoleLogger;
import com.runoob.design.chapter3.behavioral.pattern14.logger.ErrorLogger;
import com.runoob.design.chapter3.behavioral.pattern14.logger.FileLogger;

/**
 * 责任链模式（Chain of Responsibility Pattern）
 */
public class ChainPatternDemo {
	private static AbstractLogger getChainOfLoggers() {

		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		return errorLogger;
	}

	/**
	 * 创建不同类型的记录器。<br>
	 * 赋予它们不同的错误级别，并在每个记录器中设置下一个记录器。<br>
	 * 每个记录器中的下一个记录器代表的是链的一部分。<br>
	 */
	public static void main(String[] args) {
		AbstractLogger loggerChain = getChainOfLoggers();

		loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

		loggerChain.logMessage(AbstractLogger.DEBUG,
				"This is an debug level information.");

		loggerChain.logMessage(AbstractLogger.ERROR,
				"This is an error information.");
	}
}
