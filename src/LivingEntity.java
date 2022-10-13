import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;
/** An abstract class that is a parent to both the player and demon */
public abstract class LivingEntity extends GameEntity {
    private final double MAX_HEALTH;
    private double currentHealth;
    private double movementSpeed;
    private Point healthBarPos;
    private boolean isFacingRight = true;
    private HealthBar healthBar;
    private final int HEALTH_BAR_FONT_SIZE;
    /** Invincibility attributes */
    private final int INVINCIBLE_TIME = 3;
    private boolean isInvincible = false;
    private final int TOTAL_INVINCIBILITY_FRAMES = INVINCIBLE_TIME * ShadowDimension.getRefreshRate();
    private int invincibilityFrames = 0;
    /** Attacking attributes */
    private boolean isAttackMode = false;
    private boolean hasAttacked = false;
    /** Getters and setters */
    public boolean isInvincible() {
        return isInvincible;
    }
    public boolean isHasAttacked() {
        return hasAttacked;
    }
    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
    public boolean getIsAttackMode() {
        return isAttackMode;
    }
    public void setIsAttackMode(boolean isAttackMode) {
        this.isAttackMode = isAttackMode;
    }
    public double getMAX_HEALTH() {
        return MAX_HEALTH;
    }
    public boolean isFacingRight() {
        return isFacingRight;
    }
    public void setIsFacingRight(Boolean isFacingRight) {
        this.isFacingRight = isFacingRight;
    }
    public double getCurrentHealth() {
        return currentHealth;
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
    /** Constructor for a living entity */
    public LivingEntity(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health,
                        int healthBarFontSize) {
        super(position, width, height, BASE_DAMAGE, name);
        this.MAX_HEALTH = max_health;
        this.currentHealth = max_health;
        this.HEALTH_BAR_FONT_SIZE = healthBarFontSize;
        this.healthBar = new HealthBar(HEALTH_BAR_FONT_SIZE);
    }
    /**
     * Determine if the position is in the level bounds
     * @param newPos the position that a living entity is trying to move to
     * @return a boolean of whether the new position is within the level bounds
     */
    public boolean isInLevelBounds(Point newPos) {
        return ShadowDimension.getInstance().getLevelInstance().getLevelBounds().intersects(newPos);
    }
    /**
     * Determine if a living entity is colliding with a game entity
     * @param newPos the position a living entity is trying to move to
     * @param gameEntity the game entity that may be collided with
     * @return a boolean value of whether a collision occurs
     */
    public boolean isCollidingWithGameObject(Point newPos, GameEntity gameEntity) {
        this.moveTo(newPos);
        return this.intersects(gameEntity);

    }
    /**
     * Determines if a living entity moving to a new position is within the level bounds and if it collides with any of
     * the stationary entities
     * @param newPosition a position that a living entity is trying to move towards
     * @return the stationary entity that is being collided with. The level bounds, if the move is out of bounds.
     * None, if move doesn't collide with any stationary objects
     */
    public Rectangle collidingWithRectangle(Point newPosition) {
        // Check that attempted move is within the level bounds
        if (isInLevelBounds(newPosition)) {
            ArrayList<GameEntity> gameEntities = ShadowDimension.getInstance().getLevelInstance().getGameEntities();
            // Iterate through all game entities, if it is a stationary object check if the move collides with it
            for (int i = 0; i < gameEntities.size(); i++) {
                if (gameEntities.get(i) instanceof StationaryEntity) {
                    StationaryEntity stationaryEntity = (StationaryEntity) gameEntities.get(i);
                    // If the attempted move collides with a stationary object, return that object
                    if (isCollidingWithGameObject(newPosition, stationaryEntity)) {
                        return stationaryEntity;
                    }
                }
            }
            // If the move is within the bounds and is not colliding with a stationary entity return null
            return null;
        }
        // Return the level bounds if the move is out of bounds
        return ShadowDimension.getInstance().getLevelInstance().getLevelBounds();
    }
    /**
     * Damage a living entity that has been attacked
     * @param damageAmount the damage of the attacking entity
     */
    public void damageLivingEntity(double damageAmount) {
        currentHealth -= damageAmount;
        // Start the living entity's invincibility phase
        goInvincible();
        // Ensure that health cannot go below zero
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    /**
     * Update the living entity, by updating its health bar and the invincibility frames remaining
     * @param input user input
     */
    @Override
    public void updateGameEntity(Input input) {
        healthBar.updateHealthBar(healthBarPos, currentHealth, MAX_HEALTH);
        updateInvincibilityFrames();
    }
    /**
     * Create a log of the attack
     * @param attacker the game entity that attacks a living entity
     */
    public void attackLog(GameEntity attacker) {
        Log attackLog = new Log(attacker, this);
        attackLog.printAttackLog();
    }

    /**
     * Determine if the living entity is dead or not
     * @return a boolean value of the living entity's dead state
     */
    public boolean isDead() {
        if (getCurrentHealth() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void goInvincible() {
        isInvincible = true;
        invincibilityFrames = TOTAL_INVINCIBILITY_FRAMES;
    }

    public void updateInvincibilityFrames() {
        if (isInvincible) {
            if (invincibilityFrames > 0) {
                invincibilityFrames--;
            } else if (invincibilityFrames == 0) {
                isInvincible = false;
            }
        }
    }

    public abstract void attackLivingEntity();
}



