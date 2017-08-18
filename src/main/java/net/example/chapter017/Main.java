package net.example.chapter017;

public class Main {
	/**
	 * 修改锁的公平性
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PrintQueue queue = new PrintQueue();
		int size = 10;
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; i++) {
			threads[i] = new Thread(new Job(queue), "Thread " + i);
		}
		for (Thread thread : threads) {
			thread.start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
