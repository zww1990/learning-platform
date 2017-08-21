package net.example.chapter021;

public class Main {
	/**
	 * 等待多个并发事件的完成
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 10;
		VideoConference conference = new VideoConference(size);
		Thread thread = new Thread(conference);
		thread.start();
		for (int i = 0; i < size; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}
