package net.example.chapter022;

import java.util.Random;

public class MatrixMock {
	private int[][] data;

	public MatrixMock(int size, int length, int number) {
		int counter = 0;
		this.data = new int[size][length];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				this.data[i][j] = random.nextInt(10);
				if (this.data[i][j] == number) {
					counter++;
				}
			}
		}
		System.out.println("Mock: There are " + counter + " ocurrences of number in generated data.");
	}

	public int[] getRow(int row) {
		if (row >= 0 && row < this.data.length) {
			return this.data[row];
		}
		return null;
	}
}
