package net.example.chapter032;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutableTask implements Callable<String> {
	private String name;

	public ExecutableTask(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String call() throws Exception {
		long timeout = (long) (Math.random() * 10);
		System.out.println(this.name + ": Waiting " + timeout + " seconds for results.");
		TimeUnit.SECONDS.sleep(timeout);
		return "Hello, world. I'm " + this.name;
	}

}
