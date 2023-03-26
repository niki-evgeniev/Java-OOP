package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ToyRepository toyRepository;
    private List<House> houses;

    public ControllerImpl() {
        houses = new ArrayList<>();
        toyRepository = new ToyRepository();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        houses.add(house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        toyRepository.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toyRepository.findFirst(toyType);

        if (toy == null || !toyType.equals(toy.getClass().getSimpleName())) {
            String errMsg = String.format(NO_TOY_FOUND, toyType);
            throw new IllegalArgumentException(errMsg);
        }

        House house;

        for (House house1 : houses) {
            if (house1.getName().equals(houseName)) {
                house = house1;
                house1.buyToy(toy);
            }
        }
        toyRepository.removeToy(toy);


        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        House house;
        boolean catAdded = false;
        for (House house1 : houses) {
            if (house1.getName().equals(houseName)) {
                if (house1.getClass().getSimpleName().equals("LongHouse") &&
                        cat.getClass().getSimpleName().equals("LonghairCat")) {
                    house1.addCat(cat);
                    catAdded = true;
                } else if (house1.getClass().getSimpleName().equals("ShortHouse") &&
                        cat.getClass().getSimpleName().equals("ShorthairCat")) {
                    house1.addCat(cat);
                    catAdded = true;
                }
            }
        }

        if (!catAdded) {
            return String.format(UNSUITABLE_HOUSE);
        }
        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        int countCat = 0;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                Collection<Cat> cats = house.getCats();
                for (Cat cat : cats) {
                    cat.eating();
                    countCat++;
                }
            }
        }

        return String.format(FEEDING_CAT, countCat);
    }

    @Override
    public String sumOfAll(String houseName) {
        double totalSum = 0;
        for (House house : houses) {
            Collection<Cat> cats = house.getCats();
            for (Cat cat : cats) {
                totalSum += cat.getPrice();
            }
        }

        for (House house : houses) {
            for (Toy toy : house.getToys()) {
                totalSum += toy.getPrice();
            }
        }

        return String.format(VALUE_HOUSE, houseName, totalSum);
    }

    @Override
    public String getStatistics() {
        //return fields.values().stream().map(Field::getInfo)
        // .collect(Collectors.joining(System.lineSeparator()));
        return houses.stream().map(House::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }
}
