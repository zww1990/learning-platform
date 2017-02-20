package dahua.sheji.moshi.chapter16;

public class ConcreteHandler3 extends Handler {

	@Override
	public void handleRequest(int request) {
		if (request >= 20 && request < 30) {
			System.out.println(this.getClass().getName() + "处理请求" + request);
		} else if (this.successor != null) {
			this.successor.handleRequest(request);
		}
	}

}
