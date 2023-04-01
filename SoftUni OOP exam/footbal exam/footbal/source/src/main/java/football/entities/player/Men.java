package football.entities.player;

public class Men extends BasePlayer {
    public static final double KG = 85.50;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }

    @Override
    public void stimulation() {
        int currentStr = getStrength() + 115;
        setStrength(currentStr);
    }
}
