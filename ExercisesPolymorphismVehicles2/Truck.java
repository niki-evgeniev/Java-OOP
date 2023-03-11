package ExercisesPolymorphismVehicles2;

public class Truck extends Vehicles {

    public static final double WITH_AC = 1.6;
    public static final double MAX_FUEL = 0.95;

    public Truck(double quantity, double consumption, double capacity) {
        super(quantity, consumption + WITH_AC, capacity);
    }


    @Override
    public void refuel (double litters){
        super.refuel(litters * MAX_FUEL);
    }
}
