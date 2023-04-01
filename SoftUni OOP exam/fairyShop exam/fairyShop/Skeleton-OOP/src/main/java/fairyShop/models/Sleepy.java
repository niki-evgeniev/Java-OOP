package fairyShop.models;

public class Sleepy extends BaseHelper{

    private static final int ENERGY = 50;
    private static final int DECREASE_ENERGY = 5;

    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
       int energy = getEnergy();
       energy = energy - DECREASE_ENERGY;
       setEnergy(energy);
    }
}
