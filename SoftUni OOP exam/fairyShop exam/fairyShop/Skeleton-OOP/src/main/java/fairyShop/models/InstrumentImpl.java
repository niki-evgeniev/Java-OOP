package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument{
    private static final int DECREASE_POWER = 10;
    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    public void setPower(int power) {
        if (power < 0){
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        power = Math.max(0, power - DECREASE_POWER);
    }

    @Override
    public boolean isBroken() {
        return power == 0;
    }
}
