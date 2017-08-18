package net.example.chapter016;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {
	private double price1;
	private double price2;
	private ReadWriteLock lock;

	public PricesInfo() {
		this.price1 = 1;
		this.price2 = 2;
		this.lock = new ReentrantReadWriteLock();
	}

	public double getPrice1() {
		this.lock.readLock().lock();
		double value = this.price1;
		this.lock.readLock().unlock();
		return value;
	}

	public double getPrice2() {
		this.lock.readLock().lock();
		double value = this.price2;
		this.lock.readLock().unlock();
		return value;
	}

	public void setPrices(double price1, double price2) {
		this.lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		this.lock.writeLock().unlock();
	}
}
