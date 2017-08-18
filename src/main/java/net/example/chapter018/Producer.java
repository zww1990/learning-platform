package net.example.chapter018;

public class Producer implements Runnable {
	private FileMock mock;
	private Buffer buffer;

	public Producer(FileMock mock, Buffer buffer) {
		this.mock = mock;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		this.buffer.setPendingLines(true);
		while (this.mock.hasMoreLines()) {
			this.buffer.insert(this.mock.getLine());
		}
		this.buffer.setPendingLines(false);
	}

}
