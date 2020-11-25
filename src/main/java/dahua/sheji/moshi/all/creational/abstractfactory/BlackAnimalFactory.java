package dahua.sheji.moshi.all.creational.abstractfactory;

public class BlackAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		return new BlackCat();
	}

	public IDog createDog() {
		return new BlackDog();
	}
}
