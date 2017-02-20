package dahua.sheji.moshi.chapter02;

public class CashReturn implements CashSuper {
	private double moneyCondition;
	private double moneyReturn;

	public CashReturn(String moneyCondition, String moneyReturn) {
		this.moneyCondition = Double.parseDouble(moneyCondition);
		this.moneyReturn = Double.parseDouble(moneyReturn);
	}

	@Override
	public double acceptCash(double money) {
		if (money >= this.moneyCondition) {
			return money - Math.floor(money / this.moneyCondition)
					* this.moneyReturn;
		}
		return money;
	}

}
