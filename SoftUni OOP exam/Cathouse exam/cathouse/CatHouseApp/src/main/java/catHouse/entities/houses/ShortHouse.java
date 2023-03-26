package catHouse.entities.houses;

public class ShortHouse extends BaseHouse{
    private static final int CAPACITY_ON_THIS_HOUSE =  15;

    public ShortHouse(String name) {
        super(name, CAPACITY_ON_THIS_HOUSE);
    }
}
