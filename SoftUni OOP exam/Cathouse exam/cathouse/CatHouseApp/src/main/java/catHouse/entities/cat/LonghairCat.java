package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    //private static final int KG = 9;
    private int currentKg = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        int calculateKg = currentKg + 3;
        setKilograms(calculateKg);
    }
}
