package net.example.chapter036;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class LineTask extends RecursiveTask<Integer> {
	private String[] line;
	private int start;
	private int end;
	private String word;

	public LineTask(String[] line, int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;
		if (this.end - this.start < 100) {
			result = this.count();
		} else {
			int mid = (this.start + this.end) / 2;
			ForkJoinTask<Integer> t1 = new LineTask(line, start, mid, word);
			ForkJoinTask<Integer> t2 = new LineTask(line, mid, end, word);
			invokeAll(t1, t2);
			try {
				result = this.groupResults(t1.get(), t2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Integer groupResults(Integer integer, Integer integer2) {
		return integer + integer2;
	}

	private Integer count() {
		int counter = 0;
		for (int i = this.start; i < this.end; i++) {
			if (this.line[i].equals(this.word)) {
				counter++;
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return counter;
	}

}
