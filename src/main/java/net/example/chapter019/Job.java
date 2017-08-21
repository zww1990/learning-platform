package net.example.chapter019;

public class Job implements Runnable {
	private PrintQueue queue;

	public Job(PrintQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": Going to print a document");
		this.queue.printJob(new Object());
		System.out.println(Thread.currentThread().getName() + ": The document has been printed");
	}

}
