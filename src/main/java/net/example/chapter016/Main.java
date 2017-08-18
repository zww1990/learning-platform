package net.example.chapter016;

public class Main {
	/**
	 * 使用读写锁实现同步数据访问
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PricesInfo info = new PricesInfo();
		int size = 5;
		Reader[] readers = new Reader[size];
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; i++) {
			readers[i] = new Reader(info);
			threads[i] = new Thread(readers[i]);
		}
		Writer writer = new Writer(info);
		Thread threadWriter = new Thread(writer);
		for (Thread thread : threads) {
			thread.start();
		}
		threadWriter.start();
	}
}
