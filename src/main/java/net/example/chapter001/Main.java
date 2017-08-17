package net.example.chapter001;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Main {
	/**
	 * 线程信息的获取和设置
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 10;
		Thread[] threads = new Thread[size];
		Thread.State[] states = new Thread.State[size];
		for (int i = 0; i < size; i++) {
			threads[i] = new Thread(new Calculator(i));
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		try (FileWriter file = new FileWriter("d:/log.txt"); PrintWriter pw = new PrintWriter(file)) {
			for (int i = 0; i < size; i++) {
				pw.println("Main: Status of Thread " + i + " : " + threads[i].getState());
				states[i] = threads[i].getState();
			}
			for (int i = 0; i < size; i++) {
				threads[i].start();
			}
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < size; i++) {
					if (threads[i].getState() != states[i]) {
						writeThreadInfo(pw, threads[i], states[i]);
						states[i] = threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < size; i++) {
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main: Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main: Priority: %d\n", thread.getPriority());
		pw.printf("Main: Old State: %s\n", state);
		pw.printf("Main: New State: %s\n", thread.getState());
		pw.printf("Main: ************************************\n");
	}
}
