package net.example.chapter026;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	private Date initDate;
	private String name;

	public Task(String name) {
		this.name = name;
		this.initDate = new Date();
	}

	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), this.name, this.initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), this.name, new Date());
		long timeout = (long) (Math.random() * 10);
		System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), this.name,
				timeout);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), this.name, new Date());
	}

}
