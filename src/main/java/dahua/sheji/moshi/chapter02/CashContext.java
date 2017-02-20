package dahua.sheji.moshi.chapter02;

public class CashContext {
	private CashSuper cs;

	public CashContext(String type) {
		switch (type) {
		case "正常收费":
			this.cs = new CashNormal();
		case "满300返100":
			this.cs = new CashReturn("300", "100");
		case "打8折":
			this.cs = new CashRebate("0.8");
		}
	}

	public double getResult(double money) {
		if (this.cs == null) {
			return 0d;
		}
		return this.cs.acceptCash(money);
	}
}
