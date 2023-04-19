package viceCity.models.guns;

public class Pistol extends BaseGun {
    private int bullets;
    private static final int BULLET = 10;
    private static final int TOTAL_BULLET = 100;

    public Pistol(String name) {
        super(name, BULLET, TOTAL_BULLET);
        bullets = 10;
    }

    @Override
    public int fire() {
        bullets--;
        if (bullets == 0) {
            bullets = 10;
            setTotalBullets(getTotalBullets() - 10);
        }
        return 1;
    }
}
