import bagel.Input;
import bagel.util.Point;

public abstract class LivingEntity extends GameEntity {
    private final double MAX_HEALTH;
    private double currentHealth;
    private final int INVINCIBLE_TIME = 3000;
    private double movementSpeed;
    private Point healthBarPos;

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Point getHealthBarPos() {
        return healthBarPos;
    }

    public void setHealthBarPos(Point healthBarPos) {
        this.healthBarPos = healthBarPos;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public LivingEntity(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height,  BASE_DAMAGE, name);
        this.MAX_HEALTH = max_health;
        this.currentHealth = max_health;
        this.healthBarPos = healthBarPos;
    }

    public abstract void updateGameEntity(Input input);

}

