package net.example.chapter020;

public class Main {
	/**
	 * 资源的多副本的并发访问控制
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
		}
	}
}
