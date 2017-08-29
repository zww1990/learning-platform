package net.example.chapter032;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中控制任务的完成
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		int size = 5;
		ResultTask[] resultTasks = new ResultTask[size];
		for (int i = 0; i < size; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(executableTask);
			service.submit(resultTasks[i]);
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (ResultTask resultTask : resultTasks) {
			resultTask.cancel(true);
		}
		for (ResultTask resultTask : resultTasks) {
			try {
				if (!resultTask.isCancelled()) {
					System.out.println(resultTask.get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}
}
