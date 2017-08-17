package net.example.chapter007;

public class Main {
	/**
	 * 线程中不可控异常的处理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
}
