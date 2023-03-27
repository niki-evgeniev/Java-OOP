package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        drivers = new ArrayList<>();
    }

    public void setName(String name) {
        int currentSymbolForName = 5;
        if (name == null || name.trim().isEmpty()
                || name.length() < currentSymbolForName) {
            String errMsg = String.format(INVALID_NAME, name, currentSymbolForName);
            throw new IllegalArgumentException(errMsg);
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        int lap = 1;
        if (laps < lap) {
            String errMsg = String.format(INVALID_NUMBER_OF_LAPS, lap);
            throw new IllegalArgumentException(errMsg);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null){
            throw new IllegalArgumentException(DRIVER_INVALID);
        }
        if (driver.getCar() == null){
            String errMsg = String.format(DRIVER_NOT_PARTICIPATE, driver.getName());
            throw new IllegalArgumentException(errMsg);
        }
        boolean contains = drivers.contains(driver);
        if (contains){
            String errMsg = String.format(DRIVER_ALREADY_ADDED
                    , driver.getName(), name);
            throw new IllegalArgumentException(errMsg);
        }
        drivers.add(driver);
    }
}
