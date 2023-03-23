package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class FoodRepositoryImpl implements FoodRepository<BakedFood>{

    private Map<String, BakedFood> models;

    public FoodRepositoryImpl() {
        models = new LinkedHashMap<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return models.get(name);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(BakedFood bakedFood) {
        models.put(bakedFood.getName(), bakedFood);
    }
}
