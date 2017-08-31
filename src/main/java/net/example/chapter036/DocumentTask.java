package net.example.chapter036;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class DocumentTask extends RecursiveTask<Integer> {
	private String[][] document;
	private int start;
	private int end;
	private String word;

	public DocumentTask(String[][] document, int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result = 0;
		if (this.end - this.start < 10) {
			result = this.processLines();
		} else {
			int mid = (this.start + this.end) / 2;
			ForkJoinTask<Integer> t1 = new DocumentTask(document, start, mid, word);
			ForkJoinTask<Integer> t2 = new DocumentTask(document, mid, end, word);
			invokeAll(t1, t2);
			try {
				result = this.groupResults(t1.get(), t2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private int groupResults(Integer integer, Integer integer2) {
		return integer + integer2;
	}

	private int processLines() {
		List<LineTask> tasks = new ArrayList<LineTask>();
		for (int i = this.start; i < this.end; i++) {
			tasks.add(new LineTask(this.document[i], 0, this.document[i].length, this.word));
		}
		invokeAll(tasks);
		int result = 0;
		for (LineTask task : tasks) {
			try {
				result += task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
