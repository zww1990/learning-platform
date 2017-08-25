package net.example.chapter031;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中取消任务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Task task = new Task();
		System.out.println("Main: Executing the Task");
		Future<String> result = service.submit(task);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main: Canceling the Task");
		result.cancel(true);
		System.out.println("Main: Cancelled: " + result.isCancelled());
		System.out.println("Main: Done: " + result.isDone());
		service.shutdown();
		System.out.println("Main: The executor has finished");
	}
}
