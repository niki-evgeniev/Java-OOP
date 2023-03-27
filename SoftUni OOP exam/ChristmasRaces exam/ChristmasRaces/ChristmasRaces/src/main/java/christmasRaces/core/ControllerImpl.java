package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository
            , Repository<Car> carRepository
            , Repository<Race> raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driver) {

        Driver driver1 = new DriverImpl(driver);

        Driver byName = driverRepository.getByName(driver1.getName());

        if (byName != null) {
            String errMsg = String.format(DRIVER_EXISTS, driver);
            throw new IllegalArgumentException(errMsg);
        }
        driverRepository.add(driver1);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                car = null;
        }
        Car byName = carRepository.getByName(model);
        if (byName != null) {
            String errMsg = String.format(CAR_EXISTS, model);
            throw new IllegalArgumentException(errMsg);
        }
        carRepository.add(car);

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver byNameDriver = driverRepository.getByName(driverName);
        if (byNameDriver == null) {
            String errMsg = String.format(DRIVER_NOT_FOUND, driverName);
            throw new IllegalArgumentException(errMsg);
        }
        Car byNameCar = carRepository.getByName(carModel);
        if (byNameCar == null) {
            String errMsg = String.format(CAR_NOT_FOUND, carModel);
            throw new IllegalArgumentException(errMsg);
        }
        byNameDriver.addCar(byNameCar);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race byNameRace = raceRepository.getByName(raceName);
        if (byNameRace == null) {
            String errMsg = String.format(RACE_NOT_FOUND, raceName);
            throw new IllegalArgumentException(errMsg);
        }
        Driver byNameDriver = driverRepository.getByName(driverName);
        if (byNameDriver == null) {
            String errMsg = String.format(DRIVER_NOT_FOUND, driverName);
            throw new IllegalArgumentException(errMsg);
        }
        raceRepository.getByName(raceName).addDriver(byNameDriver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            String errMsg = String.format(RACE_NOT_FOUND, raceName);
            throw new IllegalArgumentException(errMsg);
        }

        int lap = race.getLaps();
        List<Driver> drivers = driverRepository.getAll()
                .stream()
                .sorted((c1, c2) -> Double.compare(c2.getCar().calculateRacePoints(lap),
                        c1.getCar().calculateRacePoints(lap)))
                .collect(Collectors.toList());

        if (drivers.size() < 3) {
            String errMsg = String.format(RACE_INVALID, raceName, 3);
            throw new IllegalArgumentException(errMsg);
        }
        raceRepository.remove(race);
       StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION,drivers.get(0).getName(), raceName))
                .append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION,drivers.get(1).getName(), raceName))
                .append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION,drivers.get(2).getName(), raceName))
                .append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race byName = raceRepository.getByName(name);
        if (byName != null) {
            String errMsg = String.format(RACE_EXISTS, name);
            throw new IllegalArgumentException(errMsg);
        }
        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
