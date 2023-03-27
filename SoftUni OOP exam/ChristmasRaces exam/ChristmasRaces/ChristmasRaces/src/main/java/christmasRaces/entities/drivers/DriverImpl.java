package christmasRaces.entities.drivers;

import christmasRaces.entities.cars.BaseCar;
import christmasRaces.entities.cars.Car;

import static christmasRaces.common.ExceptionMessages.CAR_INVALID;
import static christmasRaces.common.ExceptionMessages.INVALID_NAME;

public class DriverImpl implements Driver {
    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            int numberOfSymbol = 5;
            String errMsg = String.format(INVALID_NAME, name, numberOfSymbol);
            throw new IllegalArgumentException(errMsg);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Car getCar() {
        return car ;
    }

    @Override
    public int getNumberOfWins() {
        return numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null){
            throw new IllegalArgumentException(CAR_INVALID);
        }
        this.car = car;
    }

    @Override
    public void winRace() {
        numberOfWins = numberOfWins + 1;
    }

    @Override
    public boolean getCanParticipate() {
        return canParticipate;
    }
}
