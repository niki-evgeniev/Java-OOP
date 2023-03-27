package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private static final double CUBIC = 5000;
    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower > 600 || horsePower < 400){
            String errMsg = String.format(INVALID_HORSE_POWER, horsePower);
            throw new IllegalArgumentException(errMsg);
        }
        super.setHorsePower(horsePower);
    }
}
