package net.example.chapter030;

import java.util.Date;

public class Task implements Runnable {
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(this.name + ": Starting at: " + new Date());
	}

}
