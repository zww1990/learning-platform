package net.example.chapter014;

public class Main {
	/**
	 * 在同步代码中使用条件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventStorage storage = new EventStorage();
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);
		thread2.start();
		thread1.start();
	}
}
