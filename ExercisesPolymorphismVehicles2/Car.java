package ExercisesPolymorphismVehicles2;

public class Car extends Vehicles {

    public static final double WITH_AC = 0.9;

    public Car(double quantity, double consumption, double capacity) {
        super(quantity, consumption + WITH_AC, capacity);
    }

}
