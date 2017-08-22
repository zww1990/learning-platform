package net.example.chapter024;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {
	private Phaser phaser;

	public Student(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": Has arrived to do the exam. " + new Date());
		this.phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + ": Is going to do the first exercise. " + new Date());
		this.doExercise1();
		System.out.println(Thread.currentThread().getName() + ": Has done the first exercise. " + new Date());
		this.phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + ": Is going to do the second exercise. " + new Date());
		this.doExercise2();
		System.out.println(Thread.currentThread().getName() + ": Has done the second exercise. " + new Date());
		this.phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + ": Is going to do the third exercise. " + new Date());
		this.doExercise3();
		System.out.println(Thread.currentThread().getName() + ": Has finished the exam. " + new Date());
		this.phaser.arriveAndAwaitAdvance();
	}

	private void doExercise3() {
		long timeout = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise2() {
		long timeout = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doExercise1() {
		long timeout = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
