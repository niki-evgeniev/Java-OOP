package ExercisePolymorphismWildFarm.Animals;

import ExercisePolymorphismWildFarm.Food.Food;
import ExercisePolymorphismWildFarm.Food.Meat;

public class Zebra extends Mammal{
    public Zebra(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }
    @Override
    public void eat(Food food) {
        if (food instanceof Meat){
            throw new IllegalArgumentException(getAnimalName() + "s are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
