import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Player extends LivingEntity{
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
    private final int ATTACK_COOLDOWN = 2000;
    private final int ATTACK_TIME = 1000;
    private PlayerState playerState = PlayerState.IDLE;
    private static final int PLAYER_BASE_DAMAGE = 20;
    private static final String PLAYER_NAME = "Fae";
    private static int PLAYER_MAX_HEALTH = 100;
    private final int PLAYER_MOVEMENT_SPEED = 2;
    private final int PLAYER_HEALTH_BAR_X = 20;
    private final int PLAYER_HEALTH_BAR_Y = 25;
    private final Point PLAYER_HEALTH_BAR_POS = new Point(PLAYER_HEALTH_BAR_X, PLAYER_HEALTH_BAR_Y);
    private static int PLAYER_HEALTH_BAR_FONT_SIZE = 30;



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

        Point newPos = getNewPosition(input);

        Rectangle collidingObject = collidingWithRectangle(newPos);
        if (collidingObject == null) {
            setPosition(newPos);
        } else if (collidingObject instanceof Sinkhole){
            damageLivingEntity(((Sinkhole) collidingObject).getBASE_DAMAGE());
            attackLog((GameEntity)(collidingObject));
            ((Sinkhole) collidingObject).removeSinkhole();

        }
        drawGameEntity(currentPlayerImage());
    }

    /** Determine which image of the player should be rendered
     *
     * @return Image the current image of the player
     */
    public Image currentPlayerImage(){
        if (playerState == PlayerState.ATTACK) {
            //NEED TO DEAL WITH PLAYER WIDTH
            // setWidth(ATTACK_WIDTH);

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

    public void inflictSinkHoleDamage(Point newPos, StationaryEntity stationaryEntity){
        if (isCollidingWithStationaryObject(newPos, stationaryEntity) && stationaryEntity instanceof Sinkhole){
            damageLivingEntity(stationaryEntity.getBASE_DAMAGE());
        }
    }

    public boolean isDead(){
        if (getCurrentHealth() == 0){
            return true;
        } else{
            return false;
        }

    }
}
