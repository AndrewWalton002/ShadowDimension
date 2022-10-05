import bagel.util.Point;

public abstract class LivingEntity extends GameEntity {
    private final double MAX_HEALTH;
    private double currentHealth;
    private final int INVINCIBLE_TIME = 3000;
    private double movementSpeed;
    private Point healthBarPos;


    public LivingEntity(Point position, int width, int height, double BASE_DAMAGE, double max_health,
                        int movementSpeed, Point healthBarPos) {
        super(position, width, height, BASE_DAMAGE);
        this.MAX_HEALTH = max_health;
        this.currentHealth = max_health;
        this.movementSpeed = movementSpeed;
        this.healthBarPos = healthBarPos;
    }
}

