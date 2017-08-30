package net.example.chapter034;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		long timeout = (long) (Math.random() * 10);
		System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds", this.name, timeout);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task " + this.name + ": Ending");
	}

	@Override
	public String toString() {
		return this.name;
	}
}
