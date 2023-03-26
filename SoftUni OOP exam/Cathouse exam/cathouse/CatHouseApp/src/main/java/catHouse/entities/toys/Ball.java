package catHouse.entities.toys;

public class Ball extends BaseToy {
    private static final int SOFTNESS_CURRENT = 1;
    private static final double PRICE = 10;

    public Ball() {
        super(SOFTNESS_CURRENT, PRICE);
    }
}
