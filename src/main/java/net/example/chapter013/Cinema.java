package net.example.chapter013;

public class Cinema {
	private long vacanciesCinema1;
	private long vacanciesCinema2;
	private final Object controlCinema1;
	private final Object controlCinema2;

	public Cinema() {
		this.controlCinema1 = new Object();
		this.controlCinema2 = new Object();
		this.vacanciesCinema1 = 20;
		this.vacanciesCinema2 = 20;
	}

	public boolean sellTickets1(int number) {
		synchronized (controlCinema1) {
			if (number < vacanciesCinema1) {
				vacanciesCinema1 -= number;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean sellTickets2(int number) {
		synchronized (controlCinema2) {
			if (number < vacanciesCinema2) {
				vacanciesCinema2 -= number;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean returnTickets1(int number) {
		synchronized (controlCinema1) {
			vacanciesCinema1 += number;
			return true;
		}
	}

	public boolean returnTickets2(int number) {
		synchronized (controlCinema2) {
			vacanciesCinema2 += number;
			return true;
		}
	}

	public long getVacanciesCinema1() {
		return vacanciesCinema1;
	}

	public long getVacanciesCinema2() {
		return vacanciesCinema2;
	}
}
