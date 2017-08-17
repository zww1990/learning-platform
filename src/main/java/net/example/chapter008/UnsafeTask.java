package net.example.chapter008;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable {
	private Date startDate;

	@Override
	public void run() {
		startDate = new Date();
		System.out.println("Starting Thread: " + Thread.currentThread().getId() + " : " + startDate);
		try {
			TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Finished: " + Thread.currentThread().getId() + " : " + startDate);
	}

}
