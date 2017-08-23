package net.example.chapter026;

public class Main {
	/**
	 * 创建线程执行器
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		for (int i = 0; i < 100; i++) {
			server.execTask(new Task("Task " + i));
		}
		server.endServer();
	}
}
