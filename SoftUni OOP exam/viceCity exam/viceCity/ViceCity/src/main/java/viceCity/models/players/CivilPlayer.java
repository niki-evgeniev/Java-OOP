package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private static final int POINT = 50;

    public CivilPlayer(String name) {
        super(name, POINT);
    }
}
