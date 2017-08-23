package net.example.chapter028;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	/**
	 * 运行多个任务并处理第一个结果
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String username = "test";
		String password = "test";
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);
		List<TaskValidator> list = Arrays.asList(ldapTask, dbTask);
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			String result = service.invokeAny(list);
			System.out.println("Main: Result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
		System.out.println("Main: End of the Execution");
	}
}
