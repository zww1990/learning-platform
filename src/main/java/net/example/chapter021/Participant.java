package net.example.chapter021;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
	private VideoConference conference;
	private String name;

	public Participant(VideoConference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	@Override
	public void run() {
		long timeout = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.conference.arrive(name);
	}

}
