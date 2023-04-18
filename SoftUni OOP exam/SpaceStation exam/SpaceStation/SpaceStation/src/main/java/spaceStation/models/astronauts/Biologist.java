package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{

    private static final double OXYGEN = 70;
    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        double oxygen = getOxygen();
        oxygen = Math.max(0, oxygen - 5);
        setOxygen(oxygen);
    }
}
