package net.example.chapter028;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
	private String name;

	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String name, String password) {
		Random random = new Random();
		long timeout = (long) (Math.random() * 10);
		System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, timeout);
		try {
			TimeUnit.SECONDS.sleep(timeout);
			return random.nextBoolean();
		} catch (InterruptedException e) {
			return false;
		}
	}

	public String getName() {
		return name;
	}
}
