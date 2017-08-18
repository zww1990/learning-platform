package net.example.chapter012;

public class Main {
	/**
	 * 使用synchronized实现同步方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(1000);
		Company company = new Company(account);
		Thread companyThread = new Thread(company);
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		System.out.println("Account: Initial Balance: " + account.getBalance());
		companyThread.start();
		bankThread.start();
		try {
			companyThread.join();
			bankThread.join();
			System.out.println("Account: Final Balance: " + account.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
