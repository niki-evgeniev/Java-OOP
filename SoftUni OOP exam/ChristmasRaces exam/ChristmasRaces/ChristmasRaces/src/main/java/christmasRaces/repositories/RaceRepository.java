package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {

    private Map<String, Race> race;

    public RaceRepository() {
        race = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return race.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return race.values();
    }

    @Override
    public void add(Race model) {
        race.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return race.remove(model.getName()) != null;
    }
}
