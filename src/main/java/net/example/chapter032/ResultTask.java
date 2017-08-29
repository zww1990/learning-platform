package net.example.chapter032;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {
	private String name;

	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name = ((ExecutableTask) callable).getName();
	}

	@Override
	protected void done() {
		if (this.isCancelled()) {
			System.out.println(this.name + ": Has been canceled");
		} else {
			System.out.println(this.name + ": Has finished");
		}
	}

}
