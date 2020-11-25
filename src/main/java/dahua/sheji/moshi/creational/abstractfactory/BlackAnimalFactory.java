package dahua.sheji.moshi.creational.abstractfactory;

public class BlackAnimalFactory implements IAnimalFactory {

	public ICat createCat() {
		return new BlackCat();
	}

	public IDog createDog() {
		return new BlackDog();
	}
}
