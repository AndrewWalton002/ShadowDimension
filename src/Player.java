import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;
/** The class that represents the player in the game */
public class Player extends LivingEntity implements Moveable{
    private static final int PLAYER_BASE_DAMAGE = 20;
    private static final String PLAYER_NAME = "Fae";
    private static int PLAYER_MAX_HEALTH = 100;
    /** Image attributes */
    private final String STANDARD_LEFT_PATH = "res/fae/faeLeft.png";
    private final Image STANDARD_LEFT_IM = new Image(STANDARD_LEFT_PATH);
    private final String STANDARD_RIGHT_PATH = "res/fae/faeRight.png";
    private final Image STANDARD_RIGHT_IM = new Image(STANDARD_RIGHT_PATH);
    private final String ATTACK_LEFT_PATH = "res/fae/faeAttackLeft.png";
    private final Image ATTACK_LEFT_IM = new Image(ATTACK_LEFT_PATH);
    private final String ATTACK_RIGHT_PATH = "res/fae/faeAttackRight.png";
    private final Image ATTACK_RIGHT_IM = new Image(ATTACK_RIGHT_PATH);
    private static final int STANDARD_WIDTH = 40;
    private static final int HEIGHT = 59;
    private final int PLAYER_MOVEMENT_SPEED = 2;
    /** Health bar attributes */
    private final int PLAYER_HEALTH_BAR_X = 20;
    private final int PLAYER_HEALTH_BAR_Y = 25;
    private final Point PLAYER_HEALTH_BAR_POS = new Point(PLAYER_HEALTH_BAR_X, PLAYER_HEALTH_BAR_Y);
    private static int PLAYER_HEALTH_BAR_FONT_SIZE = 30;
    /** Attack attributes */
    private final int ATTACK_TIME = 1;
    private final int ATTACK_FRAMES = ATTACK_TIME * ShadowDimension.getRefreshRate();
    private int attackFramesLeft = ATTACK_FRAMES;
    private int attackCooldownFramesLeft = 0;
    private final int ATTACK_COOLDOWN_TIME = 2;
    private final int ATTACK_COOLDOWN_FRAMES = ATTACK_COOLDOWN_TIME * ShadowDimension.getRefreshRate();
    /** Getters */
    public static String getPlayerName(){
        return PLAYER_NAME;
    }
    public static int getPlayerBaseDamage() {
        return PLAYER_BASE_DAMAGE;
    }
    public static int getPlayerMaxHealth(){
        return PLAYER_MAX_HEALTH;
    }

    /** Constructor for the player */
    public Player(Point position, double BASE_DAMAGE, String NAME, double max_health) {
        super(position, STANDARD_WIDTH, HEIGHT, BASE_DAMAGE, NAME, max_health, PLAYER_HEALTH_BAR_FONT_SIZE);
        setHeight(HEIGHT);
        setWidth(STANDARD_WIDTH);
        setMovementSpeed(PLAYER_MOVEMENT_SPEED);
        setHealthBarPos(PLAYER_HEALTH_BAR_POS);
    }

    /**
     * Update the player, by updating the attacking phase and update the position of the player
     * @param input user input
     */
    @Override
    public void updateGameEntity(Input input) {
        super.updateGameEntity(input);
        updateAttackTimes();
        setAttackMode(input);
        // If the player has not already attacked this phase, try to attack every demon
        if (!isHasAttacked()){
            attackLivingEntity();
        }
        // If a new move is being attempted, try it, then draw the player
        Point newPos = getNewPosition(input);
        tryMove(newPos);
        drawGameEntity(currentPlayerImage());
    }
    /**
     * Determine which image of the player should be rendered
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
    /**
     * Find the position that the player is trying to move to
     * @param input user input
     * @return the position that the player is trying to move to. If the player is not trying to move, return its
     * current position
     */
    public Point getNewPosition(Input input) {
        // Set new position to the current position
        Point newPosition = getPosition();
        double movementSpeed = getMovementSpeed();
        // Update the position and orientation of the player in accordance with the input
        if (input.isDown(Keys.LEFT)) {
            newPosition = new Point(getPosition().x - movementSpeed, getPosition().y);
            setIsFacingRight(false);
        } else if (input.isDown(Keys.RIGHT)) {
            newPosition = new Point(getPosition().x + movementSpeed, getPosition().y);
            setIsFacingRight(true);
        }else if (input.isDown(Keys.UP)) {
            newPosition = new Point(getPosition().x, getPosition().y - movementSpeed);
        }else if (input.isDown(Keys.DOWN)) {
            newPosition = new Point(getPosition().x, getPosition().y + movementSpeed);
        }
        return newPosition;
    }

    /**
     * Determine if a possible move is a valid move
     * @param newPos the position to which the player is trying to move towards
     */
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

    /**
     * Update the attack state if the attack button is pressed and the player is not in attack cooldown
     * @param input user input
     */
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
    /**
     * Attack all demons that the player is colliding with
     */
    @Override
    public void attackLivingEntity(){
        ArrayList<GameEntity> gameEntities = ShadowDimension.getInstance().getLevelInstance().getGameEntities();
        // Iterate through the game entities and check if they are living entities
        for(int i = 0; i < gameEntities.size(); i++) {
            if (gameEntities.get(i) instanceof Demon) {
                Demon demon = (Demon) gameEntities.get(i);
                // If the player is in attack mode and has not already attacked and is colliding with a demon attack
                // the demon
                if (getIsAttackMode() && !isHasAttacked() &&
                    isCollidingWithGameObject(this.getPosition(), demon)) {
                    demon.damageLivingEntity(this.PLAYER_BASE_DAMAGE);
                    demon.attackLog(this);
                    // Update the has attacked boolean
                    setHasAttacked(true);
                    // If the attack kills the demon, remove it
                    if (demon.isDead()){
                        demon.removeGameEntity();
                    }
                }
            }
        }
    }
}