package net.example.chapter022;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
	private int firstRow;
	private int lastRow;
	private MatrixMock mock;
	private Results results;
	private int number;
	private final CyclicBarrier barrier;

	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		int counter;
		System.out.println(
				Thread.currentThread().getName() + ": Processing lines from " + firstRow + " to " + lastRow + ".");
		for (int i = this.firstRow; i < this.lastRow; i++) {
			int[] row = this.mock.getRow(i);
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == this.number) {
					counter++;
				}
			}
			this.results.setData(i, counter);
		}
		System.out.println(Thread.currentThread().getName() + ": Lines processed.");
		try {
			this.barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
