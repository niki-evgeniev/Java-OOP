package bakery.entities.drinks.interfaces;

public class Tea extends BaseDrink {

    private static final double PRICE_TEA = 2.50;

    public Tea(String name, int portion, String brand) {
        super(name, portion, PRICE_TEA, brand);
    }
}
