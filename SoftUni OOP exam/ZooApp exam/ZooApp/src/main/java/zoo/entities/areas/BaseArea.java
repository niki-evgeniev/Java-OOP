package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        foods = new ArrayList<>();
        animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public int sumCalories() {
        return foods.stream().mapToInt(e -> e.getCalories()).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void feed() {
        animals.forEach(animal -> animal.eat());
    }

    @Override
    public String getInfo() {
        String info = "";
        if (animals.size() == 0) {
            info = "none";
        } else {
            info = animals.stream().map(animal -> animal.getName()).collect(Collectors.joining(" "));
        }
        return String.format("%s (%s):%n" +
                        "Animals: %s%n" +
                        "Foods: %d%n" +
                        "Calories: %d"
                , name, getClass().getSimpleName()
                , info
                , foods.size()
                , sumCalories());
    }
}
