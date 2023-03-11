package ExercisesPolymorphismVehicles2;

import java.text.DecimalFormat;

public abstract class Vehicles {
    private double quantity;
    private double consumption;
    private double capacity;

    public Vehicles(double quantity, double consumption, double capacity) {
        this.quantity = quantity;
        this.consumption = consumption;
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

//    public abstract String drive(double distance);
    public String drive(double distance) {
        DecimalFormat df = new DecimalFormat("##.##");
        double needFuel = distance * getConsumption();
        double quantity = getQuantity();
        if (quantity > needFuel){
            setQuantity(quantity - needFuel);
            return String.format(getClass().getSimpleName() + " travelled %s km", df.format(distance));
        }
        return getClass().getSimpleName() + " needs refueling";
    }

    public void refuel(double litters) {
        double capacity = this.capacity;
        if (litters + quantity > capacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        } else if (0 >= litters) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        setQuantity(getQuantity() + litters);
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), getQuantity());
    }

}
