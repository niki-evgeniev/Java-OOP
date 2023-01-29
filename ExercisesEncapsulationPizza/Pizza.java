package ExercisesEncapsulationPizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setToppings(int numberOfToppings) {
        if (numberOfToppings > 10 || numberOfToppings < 0) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>();
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public void addTopping(Topping toppings) {
        this.toppings.add(toppings);
    }

    public double getOverallCalories() {
        double sum = 0;
        for (Topping topping : toppings) {
            double top = topping.calculateCalories();
            sum += top;
        }
        double doughtSum = dough.calculateCalories();
        sum += doughtSum;
        return sum;
    }
}
