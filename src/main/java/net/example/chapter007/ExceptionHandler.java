package net.example.chapter007;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("An exception has been captured");
		System.out.println("Thread: " + t.getId());
		System.out.println("Exception: " + e.getClass().getName() + ": " + e.getMessage());
		System.out.println("Stack Trace:");
		e.printStackTrace(System.out);
		System.out.println("Thread status:" + t.getState());
	}

}
