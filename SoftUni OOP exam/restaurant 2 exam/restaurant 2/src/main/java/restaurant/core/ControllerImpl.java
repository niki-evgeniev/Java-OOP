package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.*;

import java.util.Collection;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;

    private double totalBil ;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = new HealthFoodRepositoryImpl();
        this.beverageRepository = new BeverageRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood;

        switch (type) {
            case "Salad":
                healthyFood = new Salad(name, price);
                break;
            case "VeganBiscuits":
                healthyFood = new VeganBiscuits(name, price);
                break;
            default:
                healthyFood = null;
        }

        HealthyFood healthyFood1 = healthFoodRepository.foodByName(name);
        if (healthyFood1 == healthyFood) {
            String errMsg = String.format(FOOD_EXIST, name);
            throw new IllegalArgumentException(errMsg);
        }

        healthFoodRepository.add(healthyFood);

        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {

        Beverages beverages;
        switch (type) {
            case "Fresh":
                beverages = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverages = new Smoothie(name, counter, brand);
                break;
            default:
                beverages = null;

        }
        Beverages beverages1 = beverageRepository.beverageByName(name, brand);
        if (beverages1 == beverages) {
            String errMsg = String.format(BEVERAGE_EXIST, type);
            throw new IllegalArgumentException(errMsg);
        }


        beverageRepository.add(beverages);
        return String.format(BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        switch (type) {
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
            default:
                table = null;
        }

        Table table1 = tableRepository.byNumber(tableNumber);
        if (table1 != null) {
            String errMsg = String.format(TABLE_IS_ALREADY_ADDED, tableNumber);
            throw new IllegalArgumentException(errMsg);
        }

        tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection<Table> tables = tableRepository.getAllEntities();
        boolean isReserv = false;
        int numberTable = 0;
        for (Table table : tables) {
            if (table.getSize() >= numberOfPeople) {
                if (table.isReservedTable() != true) {
                    table.reserve(numberOfPeople);
                    numberTable = table.getTableNumber();
                    isReserv = true;
                    break;
                }
            }
        }
        if (!isReserv) {
            String errMsg = String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
            return errMsg;

        }

        return String.format(TABLE_RESERVED, numberTable, numberOfPeople);

    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = healthFoodRepository.foodByName(healthyFoodName);
        if (healthyFood == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(healthyFood);

        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverages = beverageRepository.beverageByName(name, brand);
        if (beverages == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }
        table.orderBeverages(beverages);
        //TODO:
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);
        double sum = table.bill();
        double allSum = table.allPeople();
        totalBil = totalBil + sum + allSum;
        table.clear();
        return String.format(BILL,tableNumber, sum + allSum);

    }


    @Override
    public String totalMoney() {
        //TODO:
        return String.format(TOTAL_MONEY, totalBil);
    }
}
