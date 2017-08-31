package net.example.chapter035;

import java.util.List;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class Task extends RecursiveAction {
	private List<Product> products;
	private int first;
	private int last;
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (this.last - this.first < 10) {
			this.updatePrices();
		} else {
			int middle = (this.last + this.first) / 2;
			System.out.println("Task: Pending tasks:" + getQueuedTaskCount());
			invokeAll(new Task(products, first, middle + 1, increment),
					new Task(products, middle + 1, last, increment));
		}
	}

	private void updatePrices() {
		for (int i = this.first; i < this.last; i++) {
			Product p = this.products.get(i);
			p.setPrice(p.getPrice() * (this.increment + 1));
		}
	}

}
