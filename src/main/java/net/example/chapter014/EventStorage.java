package net.example.chapter014;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class EventStorage {
	private int maxSize;
	private Deque<Date> storage;

	public EventStorage() {
		this.maxSize = 10;
		this.storage = new LinkedList<Date>();
	}

	public synchronized void set() {
		while (this.storage.size() == this.maxSize) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.storage.add(new Date());
		System.out.println("Set: " + this.storage.size());
		this.notifyAll();
	}

	public synchronized void get() {
		while (this.storage.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s\n", this.storage.size(), this.storage.poll());
		this.notifyAll();
	}
}
