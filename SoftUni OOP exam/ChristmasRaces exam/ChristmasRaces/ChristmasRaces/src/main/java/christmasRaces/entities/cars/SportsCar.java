package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {

    private final static double CUBIC = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower > 450 || horsePower < 250){
            String errMsg = String.format(INVALID_HORSE_POWER, horsePower);
            throw new IllegalArgumentException(errMsg);
        }
        super.setHorsePower(horsePower);
    }
}
