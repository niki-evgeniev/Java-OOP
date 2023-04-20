package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {

    private static final double KG = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KG, price);
    }

    @Override
    public void eat() {
        double currentKg = getKg() + 5.70;
        setKg(currentKg);
    }
}
