package net.example.chapter018;

import java.util.Random;

public class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (this.buffer.hasPendingLines()) {
			this.processLine(this.buffer.get());
		}
	}

	private void processLine(String string) {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
