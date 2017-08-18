package net.example.chapter018;

public class FileMock {
	private String[] content;
	private int index;

	public FileMock(int size, int length) {
		this.content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder builder = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int indice = (int) (Math.random() * 255);
				builder.append((char) indice);
			}
			this.content[i] = builder.toString();
		}
		this.index = 0;
	}

	public boolean hasMoreLines() {
		return this.index < this.content.length;
	}

	public String getLine() {
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (this.content.length - this.index));
			return this.content[this.index++];
		}
		return null;
	}
}
