package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public class BaseTable implements Table {

    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        foodOrders = new ArrayList<>();
        drinkOrders = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        double sum = numberOfPeople * pricePerPerson;
        price = sum;
        return price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
       // this.numberOfPeople = numberOfPeople;
        isReserved = true;
    }

    @Override
    public void orderFood(BakedFood food) {
        foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double foodSum = foodOrders.stream().mapToDouble(food -> food.getPrice()).sum();
        double drinkSum = drinkOrders.stream().mapToDouble(drink -> drink.getPrice()).sum();

        return foodSum + drinkSum;
    }

    @Override
    public void clear() {
        isReserved = false;
        numberOfPeople = 0;
        foodOrders.clear();
        drinkOrders.clear();
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%n" +
                        "Type: %s%n" +
                        "Capacity: %d%n" +
                        "Price per Person: %.2f"
                , tableNumber
                , getClass().getSimpleName()
                , capacity
                , pricePerPerson);
    }
}
