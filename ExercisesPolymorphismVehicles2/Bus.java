package ExercisesPolymorphismVehicles2;

import java.text.DecimalFormat;

public class Bus extends Vehicles {
    public static final double AC = 1.4;

    public Bus(double quantity, double consumption, double capacity) {
        super(quantity, consumption + AC, capacity);
    }

    public String driveEmpty(double distance) {
        setConsumption(getConsumption() - AC);
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * getConsumption();
        double quantity = getQuantity();
        if (quantity > needFuel) {
            setQuantity(quantity - needFuel);
            setConsumption(getConsumption() + AC);
            return String.format("Bus travelled %s km", df.format(distance));
        }
        return "Bus needs refueling";
    }

}
