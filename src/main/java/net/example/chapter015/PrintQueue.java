package net.example.chapter015;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private final Lock lock = new ReentrantLock();

	public void printJob(Object document) {
		this.lock.lock();
		long millis = (long) (Math.random() * 10000);
		System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (millis / 1000)
				+ " seconds");
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}
}
