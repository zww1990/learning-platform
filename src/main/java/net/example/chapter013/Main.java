package net.example.chapter013;

public class Main {
	/**
	 * 使用非依赖属性实现同步
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Cinema cinema = new Cinema();
		TicketOffice1 office1 = new TicketOffice1(cinema);
		Thread thread1 = new Thread(office1, "TicketOffice1");
		TicketOffice2 office2 = new TicketOffice2(cinema);
		Thread thread2 = new Thread(office2, "TicketOffice2");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Room1 Vacancies: " + cinema.getVacanciesCinema1());
		System.out.println("Room2 Vacancies: " + cinema.getVacanciesCinema2());
	}
}
