package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private List<Area> areas;


    public ControllerImpl() {
        foodRepository = new FoodRepositoryImpl();
        areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(INVALID_AREA_TYPE);
        }
        areas.add(area);
        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        switch (foodType) {
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
            default:
                throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }
        foodRepository.add(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            String errMsg = String.format(NO_FOOD_FOUND, foodType);
            throw new IllegalArgumentException(errMsg);
        }
        for (Area area : areas) {
            if (area.getName().equals(areaName)) {
                area.addFood(food);

            }
        }
        foodRepository.remove(food);

        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        switch (animalType) {
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }
        boolean isAdd = false;
        for (Area area : areas) {
            if (area.getClass().getSimpleName().equals("LandArea")
                    && animal.getClass().getSimpleName().equals("TerrestrialAnimal")) {
                area.addAnimal(animal);
                isAdd = true;
            } else if (area.getClass().getSimpleName().equals("WaterArea")
                    && animal.getClass().getSimpleName().equals("AquaticAnimal")) {
                area.addAnimal(animal);
                isAdd = true;
            }


        }
        if (!isAdd) {
            return String.format(AREA_NOT_SUITABLE);
        }

        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        int countFeed = 0;
        for (Area area : areas) {
            if (area.getName().equals(areaName)) {
                Collection<Animal> animals = area.getAnimals();
                for (Animal animal : animals) {
                    animal.eat();
                    countFeed++;
                }
            }
        }
        return String.format(ANIMALS_FED, countFeed);
    }

    @Override
    public String calculateKg(String areaName) {
        double sumKg = 0;
        for (Area area : areas) {
            if (area.getName().equals(areaName)) {
                sumKg = area.getAnimals().stream().mapToDouble(e -> e.getKg()).sum();
            }
        }
        return String.format(KILOGRAMS_AREA, areaName, sumKg);
    }

    @Override
    public String getStatistics() {
        return areas.stream().map(area -> area.getInfo()).collect(Collectors.joining(System.lineSeparator()));
    }
}
