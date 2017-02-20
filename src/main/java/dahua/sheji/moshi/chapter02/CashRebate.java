package dahua.sheji.moshi.chapter02;

public class CashRebate implements CashSuper {
	private double moneyRebate = 1d;

	public CashRebate(String moneyRebate) {
		this.moneyRebate = Double.parseDouble(moneyRebate);
	}

	@Override
	public double acceptCash(double money) {
		return money * this.moneyRebate;
	}

}
