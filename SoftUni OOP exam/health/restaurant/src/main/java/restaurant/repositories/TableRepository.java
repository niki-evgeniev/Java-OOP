package restaurant.repositories;

import java.util.Collection;

public class TableRepository<T> extends RepositoryEntity<T> implements restaurant.repositories.interfaces.TableRepository<T> {

    @Override
    public T byNumber(int number) {
        Collection<T> allEntities = getAllEntities();
        for (T allEntity : allEntities) {
            if (allEntity.equals(number)){
                return allEntity;
            }
        }
        return null;
    }
}
