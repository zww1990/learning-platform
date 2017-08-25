package net.example.chapter030;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中周期性执行任务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		System.out.println("Main: Starting at: " + new Date());
		Task task = new Task("Task");
		ScheduledFuture<?> result = service.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		for (int i = 0; i < 10; i++) {
			System.out.println("Main: Delay: " + result.getDelay(TimeUnit.MILLISECONDS));
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: Finished at: " + new Date());
	}
}
