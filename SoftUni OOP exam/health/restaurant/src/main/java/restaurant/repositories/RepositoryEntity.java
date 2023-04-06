package restaurant.repositories;

import restaurant.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;

public class RepositoryEntity<T> implements Repository<T> {
    private Collection<T> entities;


    @Override
    public Collection<T> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(T entity) {
        entities.add(entity);
    }
}
