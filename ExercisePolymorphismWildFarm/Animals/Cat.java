package ExercisePolymorphismWildFarm.Animals;


import ExercisePolymorphismWildFarm.Food.Food;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;
    public Cat(String animalName, String animalType, double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public void eat(Food food) {
        super.eat(food);
    }



    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %s, %d]",this.getAnimalName(),this.getAnimalType(),this.getBreed(),df.format(this.getAnimalWeight()),this.getLivingRegion(),this.getFoodEaten());
    }
}
