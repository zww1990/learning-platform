package net.example.chapter010;

import java.util.Random;

public class Task implements Runnable {

	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / (int) random.nextDouble() * 1000;
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			if (Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread().getId() + " : Interrupted");
				return;
			}
		}
	}

}
