package net.example.chapter023;

import java.util.concurrent.Phaser;

public class Main {
	/**
	 * 并发阶段任务的运行
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		FileSearch sys = new FileSearch("c:\\windows", "log", phaser);
		FileSearch apps = new FileSearch("c:\\ProgramData", "log", phaser);
		FileSearch docs = new FileSearch("c:\\Intel", "log", phaser);
		Thread sysThread = new Thread(sys, "Sys");
		Thread appsThread = new Thread(apps, "Apps");
		Thread docsThread = new Thread(docs, "Docs");
		sysThread.start();
		appsThread.start();
		docsThread.start();
		try {
			sysThread.join();
			appsThread.join();
			docsThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Terminated: " + phaser.isTerminated());
	}
}
