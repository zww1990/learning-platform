package net.example.chapter029;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中延时执行任务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		System.out.println("Main: Starting at: " + new Date());
		for (int i = 0; i < 5; i++) {
			executor.schedule(new Task("Task " + i), i + 1, TimeUnit.SECONDS);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: Ends at: " + new Date());
	}
}
