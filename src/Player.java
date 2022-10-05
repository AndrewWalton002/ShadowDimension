import bagel.util.Point;

public class Player extends LivingEntity{

    public Player(Point position, int width, int height, double BASE_DAMAGE, double max_health, int movementSpeed,
                  Point healthBarPos) {
        super(position, width, height, BASE_DAMAGE, max_health, movementSpeed, healthBarPos);
    }
}
