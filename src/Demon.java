import bagel.Input;
import bagel.util.Point;

public class Demon extends LivingEntity{
    private static final int DEMON_HEALTH_BAR_FONT_SIZE = 15;

    public Demon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health, DEMON_HEALTH_BAR_FONT_SIZE);
    }

    @Override
    public void updateGameEntity(Input input) {

    }
}
