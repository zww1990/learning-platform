package net.example.chapter023;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable {
	private String initPath;
	private String end;
	private List<String> results;
	private Phaser phaser;

	public FileSearch(String initPath, String end, Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.phaser = phaser;
		this.results = new ArrayList<String>();
	}

	private void directoryProcess(File file) {
		File[] list = file.listFiles();
		if (list != null) {
			for (File f : list) {
				if (f.isDirectory()) {
					this.directoryProcess(f);
				} else {
					this.fileProcess(f);
				}
			}
		}
	}

	private void fileProcess(File file) {
		if (file.getName().endsWith(this.end)) {
			this.results.add(file.getAbsolutePath());
		}
	}

	private void filterResults() {
		List<String> newResults = new ArrayList<String>();
		long actualDate = new Date().getTime();
		for (String path : results) {
			File file = new File(path);
			long fileDate = file.lastModified();
			if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResults.add(path);
			}
		}
		this.results = newResults;
	}

	private boolean checkResults() {
		if (this.results.isEmpty()) {
			System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), this.phaser.getPhase());
			System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), this.phaser.getPhase());
			this.phaser.arriveAndDeregister();
			return false;
		} else {
			System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), this.phaser.getPhase(),
					this.results.size());
			this.phaser.arriveAndAwaitAdvance();
			return true;
		}
	}

	private void showInfo() {
		for (String path : results) {
			File file = new File(path);
			System.out.println(Thread.currentThread().getName() + ": " + file.getAbsolutePath());
		}
		this.phaser.arriveAndAwaitAdvance();
	}

	@Override
	public void run() {
		this.phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + ": Starting.");
		File file = new File(initPath);
		if (file.isDirectory()) {
			this.directoryProcess(file);
		}
		if (!this.checkResults()) {
			return;
		}
		this.filterResults();
		if (!this.checkResults()) {
			return;
		}
		this.showInfo();
		this.phaser.arriveAndDeregister();
		System.out.println(Thread.currentThread().getName() + ": Work completed.");
	}

}
