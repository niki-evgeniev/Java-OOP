package bakery.entities.tables.interfaces;

public class InsideTable extends BaseTable {

    private static final double PRICE_PERSON = 2.50;

    public InsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, PRICE_PERSON);
    }

    @Override
    public double getBill() {
        return super.getBill();
    }
    @Override
    public double getPrice() {
        return getNumberOfPeople() * PRICE_PERSON;
    }
}
