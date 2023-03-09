package ExercisesPolymorphismVehicles;

import java.text.DecimalFormat;

public class Car extends Vehicles {

    public static final double WITH_AC = 0.9;

    public Car(double quantity, double consumption, double capacity) {
        super(quantity, consumption + WITH_AC, capacity);
    }

    @Override
    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * getConsumption();
        double quantity = getQuantity();
        if (quantity > needFuel){
            setQuantity(quantity - needFuel);
            return String.format("Car travelled %s km", df.format(distance));
        }
        return "Car needs refueling";
    }

//    @Override
//    public void refuel (double litters){
//        double capacity = getCapacity();
//        if (litters + capacity > capacity){
//            throw new IllegalArgumentException("Cannot fit fuel in tank");
//        }else if (0 >= litters){
//            throw new IllegalArgumentException("Fuel must be a positive number");
//        }
//        setQuantity(getQuantity() + litters);
//    }
}
