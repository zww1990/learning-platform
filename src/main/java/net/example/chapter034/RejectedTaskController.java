package net.example.chapter034;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskController implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("RejectedTaskController: The task " + r + " has been rejected");
		System.out.println("RejectedTaskController: " + executor);
		System.out.println("RejectedTaskController: Terminating: " + executor.isTerminating());
		System.out.println("RejectedTaskController: Terminated: " + executor.isTerminated());
	}

}
