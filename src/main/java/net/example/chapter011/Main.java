package net.example.chapter011;

public class Main {
	/**
	 * 使用工厂类创建线程
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		Task task = new Task();
		Thread thread;
		System.out.println("Starting the Threads");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		System.out.println("Factory stats:");
		System.out.println(factory.getStats());
	}
}
