package net.example.chapter025;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		int length = 10;
		for (int i = 0; i < length; i++) {
			System.out.println("Consumer: Cycle " + cycle);
			try {
				this.buffer = this.exchanger.exchange(this.buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Consumer: " + this.buffer.size());
			for (int j = 0; j < length; j++) {
				String message = this.buffer.get(0);
				System.out.println("Consumer: " + message);
				this.buffer.remove(0);
			}
			cycle++;
		}
	}

}
