import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class Player extends LivingEntity{
    private final String STANDARD_LEFT_PATH = "res/fae/faeLeft.png";
    private final Image STANDARD_LEFT_IM = new Image(STANDARD_LEFT_PATH);
    private final String STANDARD_RIGHT_PATH = "res/fae/faeLeft.png";
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
    private boolean isFacingRight = true;
    private static final int PLAYER_BASE_DAMAGE = 20;
    private static final String PLAYER_NAME = "Fae";
    private static int PLAYER_MAX_HEALTH = 100;
    private final int PLAYER_MOVEMENT_SPEED = 2;
    private final int PLAYER_HEALTH_BAR_X = 20;
    private final int PLAYER_HEALTH_BAR_Y = 25;
    private final Point PLAYER_HEALTH_BAR_POS = new Point(PLAYER_HEALTH_BAR_X, PLAYER_HEALTH_BAR_Y);



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
        super(position, STANDARD_WIDTH, HEIGHT, BASE_DAMAGE, NAME, max_health);
        setHeight(HEIGHT);
        setWidth(STANDARD_WIDTH);
        setMovementSpeed(PLAYER_MOVEMENT_SPEED);
        setHealthBarPos(PLAYER_HEALTH_BAR_POS);
    }

    @Override
    public void updateGameEntity(Input input) {
        drawGameEntity(STANDARD_LEFT_IM);
    }
}
