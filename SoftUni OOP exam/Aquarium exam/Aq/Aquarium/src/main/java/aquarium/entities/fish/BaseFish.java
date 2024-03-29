package aquarium.entities.fish;

import static aquarium.common.ExceptionMessages.*;

public class BaseFish implements Fish {
    private String name;
    private String species;
    private int size;
    private double price;

    public BaseFish(String name, String species, double price) {
        setName(name);
        setSpecies(species);
        setPrice(price);
    }


    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new NullPointerException(SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void eat() {
        size = size + 5;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
