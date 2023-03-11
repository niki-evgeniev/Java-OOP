package ExercisesPolymorphismVehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicles {

    public static final double WITH_AC = 1.6;
    public static final double MAX_FUEL = 0.95;

    public Truck(double quantity, double consumption, double capacity) {
        super(quantity, consumption + WITH_AC, capacity);
    }

    @Override
    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * getConsumption();
        double quantity = getQuantity();
        if (quantity > needFuel){
            setQuantity(quantity - needFuel);
            return String.format("Truck travelled %s km", df.format(distance));
        }
        return "Truck needs refueling";
    }

    @Override
    public void refuel (double litters){
//        double refuel = getQuantity() + (litters * 0.95);
//        setQuantity(refuel);
        super.refuel(litters * MAX_FUEL);
    }
}
