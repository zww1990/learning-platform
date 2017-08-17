package net.example.chapter006;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {
	private Deque<Event> deque;

	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;
		if (this.deque.size() == 0) {
			return;
		}
		delete = false;
		do {
			Event e = this.deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.println("Cleaner: " + e.getEvent());
				this.deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		if (delete) {
			System.out.println("Cleaner: Size of the queue: " + this.deque.size());
		}
	}
}
