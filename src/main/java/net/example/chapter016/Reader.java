package net.example.chapter016;

public class Reader implements Runnable {
	private PricesInfo pricesInfo;

	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price1: %f\n", Thread.currentThread().getName(), this.pricesInfo.getPrice1());
			System.out.printf("%s: Price2: %f\n", Thread.currentThread().getName(), this.pricesInfo.getPrice2());
		}
	}

}
