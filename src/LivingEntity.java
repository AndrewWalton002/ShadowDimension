import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public abstract class LivingEntity extends GameEntity {
    private final double MAX_HEALTH;
    private double currentHealth;
    private final int INVINCIBLE_TIME = 3000;
    private double movementSpeed;
    private Point healthBarPos;
    private boolean isFacingRight = true;
    private HealthBar healthBar;
    private final int HEALTH_BAR_FONT_SIZE;

    public double getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }
    public void setIsFacingRight(Boolean isFacingRight){
        this.isFacingRight = isFacingRight;
    }

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

    public LivingEntity(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health,
                        int healthBarFontSize) {
        super(position, width, height,  BASE_DAMAGE, name);
        this.MAX_HEALTH = max_health;
        this.currentHealth = max_health;
        this.HEALTH_BAR_FONT_SIZE = healthBarFontSize;
        this.healthBar = new HealthBar(HEALTH_BAR_FONT_SIZE);
    }

    public boolean isInLevelBounds(Point newPos){
        return ShadowDimension.getInstance().getLevelInstance().getLevelBounds().intersects(newPos);
    }

    public boolean isCollidingWithStationaryObject(Point newPos, StationaryEntity stationaryEntity){
        this.moveTo(newPos);
        return this.intersects(stationaryEntity);

    }

    public Rectangle collidingWithRectangle(Point newPosition){
        // Check that attempted move is within the level bounds
        if (isInLevelBounds(newPosition)){
            ArrayList<GameEntity> gameEntities = ShadowDimension.getInstance().getLevelInstance().getGameEntities();
            // Iterate through all game entities, if it is a stationary object check if the move collides with it
            for (int i = 0; i < gameEntities.size(); i++){
                if (gameEntities.get(i) instanceof StationaryEntity) {
                    StationaryEntity stationaryEntity = (StationaryEntity) gameEntities.get(i);
                    if(isCollidingWithStationaryObject(newPosition, stationaryEntity)){

                        return stationaryEntity;
                    }
                }
            }
            return null;
        }
        return ShadowDimension.getInstance().getLevelInstance().getLevelBounds();
    }

    public void damageLivingEntity(double damageAmount){
        currentHealth -= damageAmount;
        if (currentHealth < 0){
            currentHealth = 0;
        }
    }

    public void updateGameEntity(Input input){
        healthBar.updateHealthBar(healthBarPos, currentHealth, MAX_HEALTH);
    }

    public void attackLog(GameEntity attacker){
        Log attackLog = new Log(attacker, this);
        attackLog.printLog();
    }

    public boolean isDead(){
        if (getCurrentHealth() == 0){
            return true;
        } else{
            return false;
        }

    }
}

