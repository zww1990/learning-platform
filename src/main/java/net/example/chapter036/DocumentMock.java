package net.example.chapter036;

import java.util.Random;

public class DocumentMock {
	private String[] words = { "the", "hello", "goodbye", "pack", "java", "thread", "pool", "random", "class", "main" };

	public String[][] generateDocument(int numLines, int numWords, String word) {
		int counter = 0;
		int length = this.words.length;
		String[][] document = new String[numLines][numWords];
		Random random = new Random();
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < numWords; j++) {
				int index = random.nextInt(length);
				document[i][j] = this.words[index];
				if (document[i][j].equals(word)) {
					counter++;
				}
			}
		}
		System.out.println("DocumentMock: The word appears " + counter + " times in the document");
		return document;
	}
}
