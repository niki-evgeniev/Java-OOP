package bakery.entities.tables.interfaces;

public class OutsideTable extends BaseTable {

    private static final double PRICE_PERSON = 3.50;

    public OutsideTable(int tableNumber, int capacity) {
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
