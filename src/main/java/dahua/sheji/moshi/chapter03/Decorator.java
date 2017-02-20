package dahua.sheji.moshi.chapter03;

public abstract class Decorator extends Component {
	protected Component component;

	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		if (this.component != null) {
			this.component.operation();
		}
	}

}
