package restaurant.repositories;

public class HealthFoodRepository<T> extends RepositoryEntity<T> implements restaurant.repositories.interfaces.HealthFoodRepository<T> {
    @Override
    public T foodByName(String name) {
        for (T allEntity : getAllEntities()) {
            if (allEntity.equals(name)){
                return allEntity;
            }
        }
        return null;
    }
}
