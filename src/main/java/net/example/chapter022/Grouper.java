package net.example.chapter022;

public class Grouper implements Runnable {
	private Results results;

	public Grouper(Results results) {
		this.results = results;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.println("Grouper: Processing results...");
		int[] data = this.results.getData();
		for (int number : data) {
			finalResult += number;
		}
		System.out.println("Grouper: Total result: " + finalResult + ".");
	}

}
