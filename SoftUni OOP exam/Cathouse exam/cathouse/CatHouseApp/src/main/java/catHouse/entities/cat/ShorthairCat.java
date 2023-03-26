package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private int currentKG = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        int calculateKg = currentKG + 1;
        setKilograms(calculateKg);
    }
}
