package dahua.sheji.moshi.structural.bridge;

public class Lady extends Person {

	public Lady() {
		setType("女人");
	}

	public void dress() {
		Clothing clothing = getClothing();
		clothing.personDressCloth(this);
	}
}
