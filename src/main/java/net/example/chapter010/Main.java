package net.example.chapter010;

public class Main {
	/**
	 * 线程组中不可控异常的处理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyThreadGroup group = new MyThreadGroup("MyThreadGroup");
		Task task = new Task();
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(group, task);
			thread.start();
		}
	}
}
