package net.example.chapter034;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	/**
	 * 处理在执行器中被拒绝的任务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RejectedTaskController controller = new RejectedTaskController();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(controller);
		System.out.println("Main: Starting.");
		for (int i = 0; i < 3; i++) {
			Task task = new Task("Task " + i);
			executor.submit(task);
		}
		System.out.println("Main: Shutting down the Executor.");
		executor.shutdown();
		System.out.println("Main: Sending another Task.");
		Task task = new Task("RejectedTask");
		executor.submit(task);
		System.out.println("Main: End");
		System.out.println("Main: End.");
	}
}
