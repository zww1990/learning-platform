package net.example.chapter035;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
	/**
	 * 创建Fork/Join线程池
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		int size = 10000;
		List<Product> products = generator.generate(size);
		Task task = new Task(products, 0, size, 0.20);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.println("Main: Thread Count: " + pool.getActiveThreadCount());
			System.out.println("Main: Thread Steal: " + pool.getStealCount());
			System.out.println("Main: Parallelism: " + pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		pool.shutdown();
		if (task.isCompletedNormally()) {
			System.out.println("Main: The process has completed normally.");
		}
		for (int i = 0; i < size; i++) {
			Product p = products.get(i);
			if (p.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", p.getName(), p.getPrice());
			}
		}
		System.out.println("Main: End of the program.");
	}
}
