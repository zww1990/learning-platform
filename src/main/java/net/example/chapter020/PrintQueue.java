package net.example.chapter020;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private final Semaphore semaphore;
	private boolean freePrinters[];
	private Lock lockPrinters;

	public PrintQueue() {
		int size = 3;
		this.semaphore = new Semaphore(size);
		this.freePrinters = new boolean[size];
		for (int i = 0; i < size; i++) {
			this.freePrinters[i] = true;
		}
		this.lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			this.semaphore.acquire();
			int assignedPrinter = this.getPrinter();
			long millis = (long) (Math.random() * 10);
			System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job in Printer "
					+ assignedPrinter + " during " + (millis / 1000) + " seconds");
			TimeUnit.SECONDS.sleep(millis);
			this.freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;
		try {
			this.lockPrinters.lock();
			for (int i = 0; i < freePrinters.length; i++) {
				if (this.freePrinters[i]) {
					ret = i;
					this.freePrinters[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lockPrinters.unlock();
		}
		return ret;
	}
}
