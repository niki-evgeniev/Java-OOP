package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4) {
            int symbol = 4;
            String errMsg = String.format(INVALID_MODEL, model, symbol);
            throw new IllegalArgumentException(errMsg);
        }
        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        double calculate = cubicCentimeters / horsePower * laps;
        return calculate;
    }
}
