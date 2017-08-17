package net.example.chapter009;

import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 线程的分组
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(group, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Number of Threads: " + group.activeCount());
		System.out.println("Information about the Thread Group");
		group.list();
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads);
		for (Thread thread : threads) {
			System.out.printf("Thread %s: %s\n", thread.getName(), thread.getState());
		}
		waitFinish(group);
		group.interrupt();
	}

	private static void waitFinish(ThreadGroup group) {
		while (group.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
