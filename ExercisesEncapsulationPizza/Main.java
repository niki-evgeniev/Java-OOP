package ExercisesEncapsulationPizza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split(" ");
        String namePizza = data[1];
        int numberOfToppings = Integer.parseInt(data[2]);
        data = scanner.nextLine().split(" ");
        String flourType = data[1];
        String bakingTechnique = data[2];
        double weight = Integer.parseInt(data[3]);
        try {
            Pizza pizza = new Pizza(namePizza, numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, weight);
            pizza.setDough(dough);
            data = scanner.nextLine().split(" ");
            while (!"END".equals(data[0])) {
                String nameTopping = data[1];
                double weightTopping = Integer.parseInt(data[2]);
                Topping toppings = new Topping(nameTopping,weightTopping);
                pizza.addTopping(toppings);
                data = scanner.nextLine().split(" ");
            }
            String as = pizza.getName();
            double sum = pizza.getOverallCalories();
            System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());

        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }







    }
}
