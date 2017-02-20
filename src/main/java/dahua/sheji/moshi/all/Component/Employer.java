package dahua.sheji.moshi.all.Component;

import java.util.List;

public abstract class Employer {
	public List<Employer> employers;
	private String name;

	public abstract void add(Employer employer);

	public abstract void delete(Employer employer);

	public List<Employer> getEmployers() {
		return this.employers;
	}

	public String getName() {
		return this.name;
	}

	public void printInfo() {
		System.out.println(name);
	}

	public void setName(String name) {
		this.name = name;
	}
}
