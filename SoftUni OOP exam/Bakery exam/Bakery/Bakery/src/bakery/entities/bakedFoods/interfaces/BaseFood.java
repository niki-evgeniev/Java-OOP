package bakery.entities.bakedFoods.interfaces;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseFood implements BakedFood{

    private String name;
    private double portion;

    private double price;

    public BaseFood(String name, double portion, double price) {
        setName(name);
        setPrice(price);
        setPortion(portion);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public void setPortion(double portion) {
        if (portion <= 0){
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;
    }

    public void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fg - %.2f",name,portion, price);

    }
}
