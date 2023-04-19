package viceCity.models.guns;

public class Rifle extends BaseGun {
    private int bullets;
    private static final int BULLET = 50;
    private static final int TOTAL_BULLET = 500;

    public Rifle(String name) {
        super(name, BULLET, TOTAL_BULLET);
        this.bullets = 50;
    }

    @Override
    public int fire() {
        bullets--;
        if (bullets == 0) {
            bullets = 50;
            setTotalBullets(getTotalBullets() - 50);
        }
        return 1;
    }
}
