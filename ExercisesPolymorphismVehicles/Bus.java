package ExercisesPolymorphismVehicles;

import java.text.DecimalFormat;

public class Bus extends Vehicles {
    public static final double AC = 1.4;

    public Bus(double quantity, double consumption, double capacity) {
        super(quantity, consumption, capacity);
    }

    @Override
    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * (getConsumption() + AC);
        double quantity = getQuantity();
        if (quantity > needFuel) {
            setQuantity(quantity - needFuel);
            return String.format("Bus travelled %s km", df.format(distance));
        }
        return "Bus needs refueling";
    }

    public String driveEmpty(double distance) {
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * getConsumption();
        double quantity = getQuantity();
        if (quantity > needFuel) {
            setQuantity(quantity - needFuel);
            return String.format("Bus travelled %s km", df.format(distance));
        }
        return "Bus needs refueling";
    }

}
