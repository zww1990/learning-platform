package net.example.chapter006;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	/**
	 * 守护线程的创建和运行
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<Event>();
		WriterTask writerTask = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writerTask);
			thread.start();
		}
		CleanerTask cleanerTask = new CleanerTask(deque);
		cleanerTask.start();
	}
}
