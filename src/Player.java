import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends LivingEntity implements Moveable{
    private final String STANDARD_LEFT_PATH = "res/fae/faeLeft.png";
    private final Image STANDARD_LEFT_IM = new Image(STANDARD_LEFT_PATH);
    private final String STANDARD_RIGHT_PATH = "res/fae/faeRight.png";
    private final Image STANDARD_RIGHT_IM = new Image(STANDARD_RIGHT_PATH);
    private final String ATTACK_LEFT_PATH = "res/fae/faeAttackLeft.png";
    private final Image ATTACK_LEFT_IM = new Image(ATTACK_LEFT_PATH);
    private final String ATTACK_RIGHT_PATH = "res/fae/faeAttackRight.png";
    private final Image ATTACK_RIGHT_IM = new Image(ATTACK_RIGHT_PATH);
    private static final int ATTACK_WIDTH = 46;
    private static final int STANDARD_WIDTH = 40;
    private static final int HEIGHT = 59;
    private static final int PLAYER_BASE_DAMAGE = 20;
    private static final String PLAYER_NAME = "Fae";
    private static int PLAYER_MAX_HEALTH = 100;
    private final int PLAYER_MOVEMENT_SPEED = 2;
    private final int PLAYER_HEALTH_BAR_X = 20;
    private final int PLAYER_HEALTH_BAR_Y = 25;
    private final Point PLAYER_HEALTH_BAR_POS = new Point(PLAYER_HEALTH_BAR_X, PLAYER_HEALTH_BAR_Y);
    private static int PLAYER_HEALTH_BAR_FONT_SIZE = 30;
    private final int ATTACK_TIME = 1;
    private final int ATTACK_FRAMES = ATTACK_TIME * ShadowDimension.getRefreshRate();
    private int attackFramesLeft = ATTACK_FRAMES;
    private int attackCooldownFramesLeft = 0;
    private final int ATTACK_COOLDOWN_TIME = 2;
    private final int ATTACK_COOLDOWN_FRAMES = ATTACK_COOLDOWN_TIME * ShadowDimension.getRefreshRate();






    public static String getPlayerName(){
        return PLAYER_NAME;
    }
    public static int getPlayerBaseDamage() {
        return PLAYER_BASE_DAMAGE;
    }
    public static int getPlayerMaxHealth(){
        return PLAYER_MAX_HEALTH;
    }




    public Player(Point position, double BASE_DAMAGE, String NAME, double max_health) {
        super(position, STANDARD_WIDTH, HEIGHT, BASE_DAMAGE, NAME, max_health, PLAYER_HEALTH_BAR_FONT_SIZE);
        setHeight(HEIGHT);
        setWidth(STANDARD_WIDTH);
        setMovementSpeed(PLAYER_MOVEMENT_SPEED);
        setHealthBarPos(PLAYER_HEALTH_BAR_POS);
    }

    @Override
    public void updateGameEntity(Input input) {
        super.updateGameEntity(input);

        updateAttackTimes();
        setAttackMode(input);
        if (!isHasAttacked()){
            attackLivingEntity();
        }

        Point newPos = getNewPosition(input);
        tryMove(newPos);
        drawGameEntity(currentPlayerImage());

    }

    /** Determine which image of the player should be rendered
     *
     * @return Image the current image of the player
     */
    public Image currentPlayerImage(){
        if (getIsAttackMode()) {

            if (isFacingRight()) {
                return ATTACK_RIGHT_IM;
            } else {
                return ATTACK_LEFT_IM;
            }
        } else {
            if (isFacingRight()) {
                return STANDARD_RIGHT_IM;
            } else {
                return STANDARD_LEFT_IM;
            }

        }
    }

    public Point getNewPosition(Input input) {
        Point newPosition = getPosition();

        // Update the position and orientation of the player in accordance with the input
        if (input.isDown(Keys.LEFT)) {
            newPosition = new Point(getPosition().x - getMovementSpeed(), getPosition().y);
            setIsFacingRight(false);
        } else if (input.isDown(Keys.RIGHT)) {
            newPosition = new Point(getPosition().x + getMovementSpeed(), getPosition().y);
            setIsFacingRight(true);
        }else if (input.isDown(Keys.UP)) {
            newPosition = new Point(getPosition().x, getPosition().y - getMovementSpeed());
        }else if (input.isDown(Keys.DOWN)) {
            newPosition = new Point(getPosition().x, getPosition().y + getMovementSpeed());
        }
        return newPosition;
    }


    @Override
    public void tryMove(Point newPos) {
        Rectangle collidingObject = collidingWithRectangle(newPos);
        if (collidingObject == null) {
            setPosition(newPos);
        } else if (collidingObject instanceof Sinkhole){
            damageLivingEntity(((Sinkhole) collidingObject).getBASE_DAMAGE());
            attackLog((GameEntity)(collidingObject));
            ((Sinkhole) collidingObject).removeGameEntity();

        }
    }

    public void setAttackMode(Input input){
        if (input.wasPressed(Keys.A) && attackCooldownFramesLeft == 0){
            attackFramesLeft = ATTACK_FRAMES;
            setIsAttackMode(true);
        }
    }

    public void updateAttackTimes() {
        if (getIsAttackMode()) {
            if (attackFramesLeft > 0) {
                attackFramesLeft--;
            } else if (attackFramesLeft == 0) {
                setIsAttackMode(false);
                setHasAttacked(false);
                attackCooldownFramesLeft = ATTACK_COOLDOWN_FRAMES;
            }
        } else {
            if (attackCooldownFramesLeft > 0) {
                attackCooldownFramesLeft--;
            }
        }
    }
    @Override
    public void attackLivingEntity(){
        ArrayList<GameEntity> gameEntities = ShadowDimension.getInstance().getLevelInstance().getGameEntities();

        for(int i = 0; i < gameEntities.size(); i++) {
            if (gameEntities.get(i) instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) gameEntities.get(i);

                if (getIsAttackMode() && !isHasAttacked() &&
                    isCollidingWithGameObject(this.getPosition(), livingEntity)) {
                    livingEntity.damageLivingEntity(this.PLAYER_BASE_DAMAGE);
                    livingEntity.attackLog(this);
                    setHasAttacked(true);

                    if (livingEntity.isDead()){
                        livingEntity.removeGameEntity();
                    }
                }
            }
        }
    }


}
