package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiscovererRepository implements Repository<Discoverer>{

    private Collection<Discoverer> discoverers;

    public DiscovererRepository() {
        discoverers = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return discoverers.remove(entity);
    }

    @Override
    public Discoverer byName(String name) {
        for (Discoverer discoverer : discoverers) {
            if (discoverer.getName().equals(name)){
                return discoverer;
            }
        }

        return discoverers.stream().filter(discoverer -> discoverer.getName().equals(name))
                .findFirst().orElse(null);
    }
}
