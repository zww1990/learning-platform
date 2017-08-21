package net.example.chapter019;

import java.util.concurrent.Semaphore;

public class PrintQueue {
	private final Semaphore semaphore;

	public PrintQueue() {
		this.semaphore = new Semaphore(1);
	}

	public void printJob(Object document) {
		try {
			this.semaphore.acquire();
			long millis = (long) (Math.random() * 10);
			System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
					+ (millis / 1000) + " seconds");
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.semaphore.release();
		}
	}
}
