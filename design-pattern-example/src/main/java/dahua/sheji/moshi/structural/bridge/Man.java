package dahua.sheji.moshi.structural.bridge;

public class Man extends Person {

	public Man() {
		setType("男人");
	}

	public void dress() {
		Clothing clothing = getClothing();
		clothing.personDressCloth(this);
	}
}
