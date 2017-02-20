package dahua.sheji.moshi.chapter12;

public class HRDepartment extends Company {

	public HRDepartment(String name) {
		super(name);
	}

	@Override
	public void add(Company c) {

	}

	@Override
	public void remove(Company c) {

	}

	@Override
	public void display(int depth) {
		System.out.println(StringUtils.repeat("-", depth) + this.name);
	}

	@Override
	public void lineOfDuty() {
		System.out.println(this.name + " 员工招聘培训管理");
	}

}
