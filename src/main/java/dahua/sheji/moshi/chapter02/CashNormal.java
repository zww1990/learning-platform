package dahua.sheji.moshi.chapter02;

public class CashNormal implements CashSuper {

	@Override
	public double acceptCash(double money) {
		return money;
	}

}
