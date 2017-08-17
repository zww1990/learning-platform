package net.example.chapter004;

import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 线程的休眠和恢复
	 * @param args
	 */
	public static void main(String[] args) {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
