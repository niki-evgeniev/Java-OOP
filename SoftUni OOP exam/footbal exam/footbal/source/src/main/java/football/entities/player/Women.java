package football.entities.player;

public class Women extends BasePlayer {
    public static final double KG = 60.00;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }

    @Override
    public void stimulation() {
        int currentStr = getStrength() + 145;
        setStrength(currentStr);
    }
}
