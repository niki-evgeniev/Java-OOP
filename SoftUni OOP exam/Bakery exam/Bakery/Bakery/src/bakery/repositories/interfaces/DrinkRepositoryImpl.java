package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DrinkRepositoryImpl implements DrinkRepository<Drink> {

    private List<Drink> models;

    public DrinkRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return models.stream().filter(e -> e.getBrand().equals(drinkBrand) &&
                        e.getName().equals(drinkName))
                .findFirst().orElse(null);

    }

    @Override
    public Collection<Drink> getAll() {
        return models;
    }

    @Override
    public void add(Drink drink) {
        models.add(drink);
    }
}
