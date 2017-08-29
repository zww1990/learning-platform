package net.example.chapter033;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 在执行器中分离任务的启动与结果的处理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<String>(executor);
		ReportRequest faceRequest = new ReportRequest("Face", service);
		ReportRequest onlineRequest = new ReportRequest("Online", service);
		Thread faceThread = new Thread(faceRequest);
		Thread onlineThread = new Thread(onlineRequest);
		ReportProcessor processor = new ReportProcessor(service);
		Thread senderThread = new Thread(processor);
		System.out.println("Main: Starting the Threads");
		faceThread.start();
		onlineThread.start();
		senderThread.start();
		try {
			System.out.println("Main: Waiting for the report generators.");
			faceThread.join();
			onlineThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Main: Shutting down the executor.");
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		processor.setEnd(true);
		System.out.println("Main: Ends");
	}
}
