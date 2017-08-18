package net.example.chapter018;

public class Main {
	/**
	 * 在锁中使用多条件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FileMock mock = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		Producer producer = new Producer(mock, buffer);
		Thread thread = new Thread(producer, "Producer");
		int size = 3;
		Consumer[] consumers = new Consumer[size];
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; i++) {
			consumers[i] = new Consumer(buffer);
			threads[i] = new Thread(consumers[i], "Consumer " + i);
		}
		thread.start();
		for (Thread thread2 : threads) {
			thread2.start();
		}
	}
}
