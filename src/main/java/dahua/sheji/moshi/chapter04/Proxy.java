package dahua.sheji.moshi.chapter04;

public class Proxy implements Subject {
	private RealSubject rs;

	@Override
	public void request() {
		if (this.rs == null) {
			this.rs = new RealSubject();
		}
		this.rs.request();
	}

}
