package net.example.chapter025;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		int length = 10;
		for (int i = 0; i < length; i++) {
			System.out.println("Producer: Cycle " + cycle);
			for (int j = 0; j < length; j++) {
				String message = "Event " + (i * 10 + j);
				System.out.println("Producer: " + message);
				this.buffer.add(message);
			}
			try {
				this.buffer = this.exchanger.exchange(this.buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer: " + this.buffer.size());
			cycle++;
		}
	}

}
