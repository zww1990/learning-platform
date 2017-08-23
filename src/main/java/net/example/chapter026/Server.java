package net.example.chapter026;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	private ThreadPoolExecutor executor;

	public Server() {
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}

	public void execTask(Task task) {
		System.out.println("Server: A new task has arrived");
		this.executor.execute(task);
		System.out.println("Server: Pool Size: " + this.executor.getPoolSize());
		System.out.println("Server: Active Count: " + this.executor.getActiveCount());
		System.out.println("Server: Completed Tasks: " + this.executor.getCompletedTaskCount());
		System.out.println("Server: Task Count: " + this.executor.getTaskCount());
	}

	public void endServer() {
		this.executor.shutdown();
	}
}
