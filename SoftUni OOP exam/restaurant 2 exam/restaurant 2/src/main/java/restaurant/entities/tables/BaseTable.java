package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood ;
    private Collection<Beverages> beverage;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable = false;
    private double allPeople;


    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        healthyFood = new ArrayList<>();
        beverage = new ArrayList<>();
    }


    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        double sum = pricePerPerson * numberOfPeople;
        allPeople = sum;
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        beverage.add(beverages);
    }

    @Override
    public double bill() {
        double sum1 = healthyFood.stream().mapToDouble(e -> e.getPrice()).sum();
        double sum2 = beverage.stream().mapToDouble(e -> e.getPrice()).sum();
        return sum2 + sum1;
//        double sum = numberOfPeople * pricePerPerson;
//        return sum;
    }

    @Override
    public void clear() {
        isReservedTable = false;
        numberOfPeople = 0;
        healthyFood.clear();
        beverage.clear();
    }

    @Override
    public String tableInformation() {
        return String.format("Table - %d%n" +
                        "Size - %d%n" +
                        "Type - %s%n" +
                        "All price - ", number
                , size,
                getClass().getSimpleName(),
                allPeople);
    }
}
