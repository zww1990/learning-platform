package net.example.chapter028;

import java.util.concurrent.Callable;

public class TaskValidator implements Callable<String> {
	private UserValidator validator;
	private String user;
	private String password;

	public TaskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!this.validator.validate(this.user, this.password)) {
			System.out.println(this.validator.getName() + ": The user has not been found");
			throw new Exception("Error validating user");
		}
		System.out.println(this.validator.getName() + ": The user has been found");
		return this.validator.getName();
	}

}
