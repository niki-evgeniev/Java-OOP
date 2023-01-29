package ExercisePolymorphismWildFarm;

import ExercisePolymorphismWildFarm.Animals.*;
import ExercisePolymorphismWildFarm.Food.Food;
import ExercisePolymorphismWildFarm.Food.Meat;
import ExercisePolymorphismWildFarm.Food.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] data = scanner.nextLine().split(" ");
        List<Animal> animals = new ArrayList<>();

        while (!data[0].equals("End")) {
            Animal animal = null;
            String[] food = scanner.nextLine().split(" ");

            Food food1 = null;

            if (food[0].equals("Vegetable")){
                food1 = new Vegetable(Integer.parseInt(food[1]));
            }else {
                food1 = new Meat(Integer.parseInt(food[1]));
            }

            switch (data[0]) {
                case "Cat":
                    animal = new Cat(data[0], data[1], Double.parseDouble(data[2]), data[3], data[4]);
                    break;
                case "Tiger":
                    animal = new Tiger(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                    break;
                case "Zebra":
                    animal = new Zebra(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                    break;
                case "Mouse":
                    animal = new Mouse(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                    break;
            }
            animal.makeSound();

            try {
                animal.eat(food1);
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }

            animals.add(animal);

            data = scanner.nextLine().split(" ");

        }
//        for (Animal animal : animals) {
//            System.out.println(animal);
//        }
        animals.stream()
                .forEach(System.out::println);
    }
}
