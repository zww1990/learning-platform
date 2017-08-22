package net.example.chapter024;

public class Main {
	/**
	 * 并发阶段任务中的阶段切换
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyPhaser phaser = new MyPhaser();
		int size = 5;
		Student[] students = new Student[size];
		for (int i = 0; i < size; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}
		Thread[] threads = new Thread[size];
		for (int i = 0; i < size; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main: The phaser has finished: " + phaser.isTerminated() + ".");
	}
}
