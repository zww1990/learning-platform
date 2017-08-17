package net.example.chapter003;

import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 线程中断的控制
	 * @param args
	 */
	public static void main(String[] args) {
		FileSearch search = new FileSearch("d:\\", "readme.txt");
		Thread thread = new Thread(search);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
