package dahua.sheji.moshi.chapter12;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCompany extends Company {
	private List<Company> children = new ArrayList<>();

	public ConcreteCompany(String name) {
		super(name);
	}

	@Override
	public void add(Company c) {
		this.children.add(c);
	}

	@Override
	public void remove(Company c) {
		this.children.remove(c);
	}

	@Override
	public void display(int depth) {
		System.out.println(StringUtils.repeat("-", depth) + this.name);
		for (Company company : children) {
			company.display(depth + 2);
		}
	}

	@Override
	public void lineOfDuty() {
		for (Company company : children) {
			company.lineOfDuty();
		}
	}

}
