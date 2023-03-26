package catHouse.entities.toys;

public class Mouse extends BaseToy {

    private static final int SOFTNESS_CURRENT = 5;
    private static final double PRICE = 15;

    public Mouse() {
        super(SOFTNESS_CURRENT, PRICE);
    }
}
