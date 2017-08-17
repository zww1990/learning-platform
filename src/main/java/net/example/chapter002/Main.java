package net.example.chapter002;

public class Main {
	/**
	 * 线程的中断
	 * @param args
	 */
	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.interrupt();
	}
}
