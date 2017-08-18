package net.example.chapter013;

public class TicketOffice2 implements Runnable {
	private Cinema cinema;

	public TicketOffice2(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {
		this.cinema.sellTickets2(2);
		this.cinema.sellTickets2(4);
		this.cinema.sellTickets1(2);
		this.cinema.sellTickets1(1);
		this.cinema.returnTickets2(2);
		this.cinema.sellTickets1(3);
		this.cinema.sellTickets2(2);
		this.cinema.sellTickets1(2);
	}

}
