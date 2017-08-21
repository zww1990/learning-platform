package net.example.chapter021;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
	private final CountDownLatch controller;

	public VideoConference(int number) {
		this.controller = new CountDownLatch(number);
	}

	@Override
	public void run() {
		System.out.println("Video Conference: Initialization: " + this.controller.getCount() + " participants.");
		try {
			this.controller.await();
			System.out.println("Video Conference: All the participants have come");
			System.out.println("Video Conference: Let's start...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void arrive(String name) {
		System.out.println(name + " has arrived.");
		this.controller.countDown();
		System.out.println("Video Conference: Waiting for " + this.controller.getCount() + " participants.");
	}
}
