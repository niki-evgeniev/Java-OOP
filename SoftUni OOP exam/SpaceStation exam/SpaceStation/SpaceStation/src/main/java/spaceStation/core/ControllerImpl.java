package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;

    private int count;

    public ControllerImpl() {
        astronautRepository = new AstronautRepository();
        planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);

        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        for (String item : items) {
            planet.getItems().add(item);
        }

        planetRepository.add(planet);

        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {

        Astronaut byName = astronautRepository.findByName(astronautName);
        if (byName == null) {
            String errMsg = String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName);
            throw new IllegalArgumentException(errMsg);
        }

        astronautRepository.remove(byName);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        List<Astronaut> collect = astronautRepository.getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet planet = planetRepository.findByName(planetName);

        Mission mission = new MissionImpl();
        mission.explore(planet, collect);

        long count1 = collect.stream().filter(a -> a.getOxygen() == 0).count();
        count++;

        return String.format(PLANET_EXPLORED, planetName, count1);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(REPORT_PLANET_EXPLORED, count)).append(System.lineSeparator());
        sb.append(String.format(REPORT_ASTRONAUT_INFO)).append(System.lineSeparator());
        for (Astronaut model : astronautRepository.getModels()) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, model.getName())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, model.getOxygen())).append(System.lineSeparator());
            String info;
            Collection<String> items = model.getBag().getItems();
            if (items.isEmpty()) {
                info = "none";
            } else {
                info = items.stream()
                        .collect(Collectors.joining(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER));
            }
            sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,info))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
