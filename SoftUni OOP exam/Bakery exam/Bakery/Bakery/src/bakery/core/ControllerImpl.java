package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.bakedFoods.interfaces.Bread;
import bakery.entities.bakedFoods.interfaces.Cake;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.drinks.interfaces.Tea;
import bakery.entities.drinks.interfaces.Water;
import bakery.entities.tables.interfaces.BaseTable;
import bakery.entities.tables.interfaces.InsideTable;
import bakery.entities.tables.interfaces.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static bakery.common.ExceptionMessages.*;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double allMoney;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {

        this.foodRepository = new FoodRepositoryImpl();
        this.drinkRepository = new DrinkRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();

    }


    @Override
    public String addFood(String type, String name, double price) {

        BakedFood byName = foodRepository.getByName(name);
        if (byName != null) {
            String errMsg = String.format(FOOD_OR_DRINK_EXIST, type, name);
            throw new IllegalArgumentException(errMsg);
        }
        BakedFood bakedFood;
        switch (type) {
            case "Bread":
                bakedFood = new Bread(name, price);
                break;
            case "Cake":
                bakedFood = new Cake(name, price);
                break;
            default:
                bakedFood = null;
        }

        foodRepository.add(bakedFood);

        return String.format(FOOD_ADDED, name, type);

    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink byNameAndBrand = drinkRepository.getByNameAndBrand(name, brand);

        if (byNameAndBrand != null) {
            String errMsg = String.format(FOOD_OR_DRINK_EXIST, type, name);
            throw new IllegalArgumentException(errMsg);
        }
        Drink drink;
        switch (type) {
            case "Tea":
                drink = new Tea(name, portion, brand);
                break;
            case "Water":
                drink = new Water(name, portion, brand);
                break;
            default:
                drink = null;
        }
        drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table byNumber = tableRepository.getByNumber(tableNumber);
        if (byNumber != null) {
            String errMsg = String.format(TABLE_EXIST, tableNumber);
        }
        Table table;
        switch (type) {
            case "InsideTable":
                table = new InsideTable(tableNumber, capacity);
                break;
            case "OutsideTable":
                table = new OutsideTable(tableNumber, capacity);
                break;
            default:
                table = null;
        }
        tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Collection<Table> allTable = tableRepository.getAll();
        boolean isReserve = false;
        int numberOfTable = 0;
        for (Table table : allTable) {
            if (table.getCapacity() >= numberOfPeople) {
                if (table.isReserved() != true) {
                    table.reserve(numberOfPeople);
                    numberOfTable = table.getTableNumber();
                    isReserve = true;
                    break;
                }
            }
        }
        if (!isReserve) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        return String.format(TABLE_RESERVED, numberOfTable, numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table byNumberTable = tableRepository.getByNumber(tableNumber);
        if (byNumberTable == null || !byNumberTable.isReserved()) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood byNameFood = foodRepository.getByName(foodName);
        if (byNameFood == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }
        tableRepository.getByNumber(tableNumber).orderFood(byNameFood);

        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table byNumberTable = tableRepository.getByNumber(tableNumber);
        if (byNumberTable == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Drink byNameAndBrand = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (byNameAndBrand == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }
        byNumberTable.orderDrink(byNameAndBrand);


        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = tableRepository.getByNumber(tableNumber);
        double sum = table.getBill();
        double sum2 = table.getPrice();
        allMoney += sum2 + sum;
        table.clear();
       return String.format(BILL, tableNumber, sum2 + sum);
    }

    @Override
    public String getFreeTablesInfo() {
        Collection<Table> allTable = tableRepository.getAll();
        StringBuilder sb = new StringBuilder();
        List<Table> collect = allTable.stream().filter(table -> !table.isReserved()).collect(Collectors.toList());
        for (Table table : collect) {
            sb.append(table.getFreeTableInfo()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, allMoney);

    }
}
