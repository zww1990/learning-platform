package net.example.chapter008;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
		protected Date initialValue() {
			return new Date();
		};
	};

	@Override
	public void run() {
		System.out.println("Starting Thread: " + Thread.currentThread().getId() + " : " + startDate.get());
		try {
			TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Finished: " + Thread.currentThread().getId() + " : " + startDate.get());
	}

}
