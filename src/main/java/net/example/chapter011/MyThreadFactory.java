package net.example.chapter011;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	private int counter;
	private String name;
	private List<String> stats;

	public MyThreadFactory(String name) {
		this.name = name;
		this.counter = 0;
		this.stats = new ArrayList<String>();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, name + "-Thread_" + this.counter);
		this.counter++;
		this.stats.add(
				String.format("Created thread %d with name %s on %s\n", thread.getId(), thread.getName(), new Date()));
		return thread;
	}

	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = this.stats.iterator();
		while (it.hasNext()) {
			String string = it.next();
			buffer.append(string);
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
