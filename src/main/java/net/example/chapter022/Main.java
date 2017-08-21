package net.example.chapter022;

import java.util.concurrent.CyclicBarrier;

public class Main {
	/**
	 * 在集合点的同步
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int rows = 10000;
		int numbers = 1000;
		int search = 5;
		int participants = 5;
		int line_participant = 2000;
		MatrixMock mock = new MatrixMock(rows, numbers, search);
		Results results = new Results(rows);
		Grouper grouper = new Grouper(results);
		CyclicBarrier barrier = new CyclicBarrier(participants, grouper);
		Searcher[] searchers = new Searcher[participants];
		for (int i = 0; i < participants; i++) {
			searchers[i] = new Searcher(i * line_participant, (i * line_participant) + line_participant, mock, results,
					search, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		System.out.println("Main: The main thread has finished.");
	}
}
