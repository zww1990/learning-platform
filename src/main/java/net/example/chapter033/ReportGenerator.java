package net.example.chapter033;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ReportGenerator implements Callable<String> {
	private String sender;
	private String title;

	public ReportGenerator(String sender, String title) {
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {
		long timeout = (long) (Math.random() * 10);
		System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender, this.title,
				timeout);
		TimeUnit.SECONDS.sleep(timeout);
		return this.sender + ": " + this.title;
	}

}
