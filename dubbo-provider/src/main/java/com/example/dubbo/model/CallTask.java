package com.example.dubbo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class CallTask extends RecursiveTask<List<User>> {
	private static final int THRESHOLD = 1000;
	private List<User> users;
	private int start;
	private int end;

	public CallTask(List<User> users, int start, int end) {
		super();
		this.users = users;
		this.start = start;
		this.end = end;
	}

	@Override
	protected List<User> compute() {
		if (end - start < THRESHOLD) {
			List<User> result = new ArrayList<>();
			for (int i = start; i < end; i++) {
				User user = users.get(i);
				if (user.getName().contains("520")) {
					result.add(user);
				}
			}
			return result;
		} else {
			int middle = (start + end) / 2;
			CallTask left = new CallTask(users, start, middle);
			CallTask right = new CallTask(users, middle, end);
			left.fork();
			right.fork();
			List<User> result = new ArrayList<>();
			result.addAll(left.join());
			result.addAll(right.join());
			return result;
		}
	}

}
