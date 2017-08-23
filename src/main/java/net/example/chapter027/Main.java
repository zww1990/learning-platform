package net.example.chapter027;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中执行任务并返回结果
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		Random random = new Random();
		int length = 10;
		for (int i = 0; i < length; i++) {
			list.add(executor.submit(new FactorialCalculator(random.nextInt(length))));
		}
		do {
			System.out.println("Main: Number of Completed Tasks: " + executor.getCompletedTaskCount());
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("Main: Task %d: %s\n", i, list.get(i).isDone());
			}
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < list.size());
		System.out.println("Main: Results");
		for (int i = 0; i < list.size(); i++) {
			Integer number = null;
			try {
				number = list.get(i).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		executor.shutdown();
	}
}
