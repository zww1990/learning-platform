package net.example.chapter033;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	private CompletionService<String> service;
	private boolean end;

	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		this.end = false;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	@Override
	public void run() {
		while (!this.end) {
			try {
				Future<String> result = this.service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					System.out.println("ReportReceiver: Report Received: " + result.get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ReportSender: End");
	}

}
