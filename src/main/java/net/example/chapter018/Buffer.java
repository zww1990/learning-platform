package net.example.chapter018;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	private Deque<String> buffer;
	private int maxSize;
	private Lock lock;
	private Condition lines;
	private Condition space;
	private boolean pendingLines;

	public Buffer(int maxSize) {
		this.maxSize = maxSize;
		this.buffer = new LinkedList<String>();
		this.lock = new ReentrantLock();
		this.lines = this.lock.newCondition();
		this.space = this.lock.newCondition();
		this.pendingLines = true;
	}

	public void insert(String line) {
		this.lock.lock();
		try {
			while (this.buffer.size() == this.maxSize) {
				this.space.await();
			}
			this.buffer.offer(line);
			System.out.println(Thread.currentThread().getName() + ": Inserted Line: " + this.buffer.size());
			this.lines.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

	public String get() {
		String line = null;
		this.lock.lock();
		try {
			while (this.buffer.size() == 0 && this.hasPendingLines()) {
				this.lines.await();
			}
			if (this.hasPendingLines()) {
				line = this.buffer.poll();
				System.out.println(Thread.currentThread().getName() + ": Line Readed: " + this.buffer.size());
				this.space.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		return line;
	}

	public boolean hasPendingLines() {
		return this.pendingLines || this.buffer.size() > 0;
	}

	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}
}
