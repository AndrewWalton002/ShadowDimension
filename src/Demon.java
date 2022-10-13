import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import java.util.Random;
/** The class of the passive demon that can be extended by agressive demon */
public class Demon extends LivingEntity{
    private static int DEMON_DAMAGE = 10;
    private static int DEMON_MAX_HEALTH = 40;
    private static int DEMON_SPEED = 0;
    /** Image attributes */
    private static final String DEMON_LEFT_PATH = "res/demon/demonLeft.png";
    private final Image DEMON_LEFT_IMAGE = new Image(DEMON_LEFT_PATH);
    private static final String DEMON_RIGHT_PATH = "res/demon/demonRight.png";
    private final Image DEMON_RIGHT_IMAGE = new Image(DEMON_RIGHT_PATH);
    private static final String DEMON_INVINCIBLE_LEFT_PATH = "res/demon/demonInvincibleLeft.PNG";
    private final Image DEMON_INVINCIBLE_LEFT_IMAGE = new Image(DEMON_INVINCIBLE_LEFT_PATH);
    private static final String DEMON_INVINCIBLE_RIGHT_PATH = "res/demon/demonInvincibleRight.PNG";
    private final Image DEMON_INVINCIBLE_RIGHT_IMAGE = new Image(DEMON_INVINCIBLE_RIGHT_PATH);
    private static final int DEMON_WIDTH = 60;
    private static final int DEMON_HEIGHT = 38;
    /** Health bar attributes */
    private static final int HEALTH_BAR_Y_OFFSET = 6;
    private HealthBar healthBar = new HealthBar(DEMON_HEALTH_BAR_FONT_SIZE);
    private static final String DEMON_NAME = "Demon";
    private static final int DEMON_HEALTH_BAR_FONT_SIZE = 15;
    /** Demon fire attributes */
    private int FIRE_RANGE = 150;
    private final Fire DEMON_FIRE = new Fire(this,
                                            ShadowDimension.getInstance().getLevelInstance().getPlayer());
    private Fire fire;
    /** Getters and setters */
    public int getFireRange(){
        return FIRE_RANGE;
    }
    public static int getDemonMaxHealth(){
        return DEMON_MAX_HEALTH;
    }
    public static String getDemonName(){
        return DEMON_NAME;
    }
    public static int getDemonWidth(){
        return DEMON_WIDTH;
    }
    public static int getDemonHeight(){
        return DEMON_HEIGHT;
    }
    public static int getDemonDamage(){
        return DEMON_DAMAGE;
    }
    public Image getLeftImage(){
        return DEMON_LEFT_IMAGE;
    }
    public Image getRightImage(){
        return DEMON_RIGHT_IMAGE;
    }
    public Image getInvincibleLeftImage(){
        return DEMON_INVINCIBLE_LEFT_IMAGE;
    }
    public Image getInvincibleRightImage(){
        return DEMON_INVINCIBLE_RIGHT_IMAGE;
    }
    public void setFire(Fire fire){
        this.fire = fire;
    }
    /** Constructor for the demon */
    public Demon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health, DEMON_HEALTH_BAR_FONT_SIZE);
        setMovementSpeed(DEMON_SPEED);
        setDemonFacingDirection();
        setHealthBarPos(setDemonHealthBarPos());
        setFire(DEMON_FIRE);
    }
    /**
     * Update and draw the demon
     * @param input user input
     */
    @Override
    public void updateGameEntity(Input input) {
        setHealthBarPos(setDemonHealthBarPos());
        drawGameEntity(getDemonImage(getInvincibleLeftImage(), getInvincibleRightImage(), getLeftImage(),
                                    getRightImage()));
        fire.updateFire();
        super.updateGameEntity(input);
    }
    /**
     * Set the way the demon originally faces randomly
     */
    public void setDemonFacingDirection(){
        Random rand = new Random();
        if (rand.nextInt() % 2 != 0) {
            setIsFacingRight(true);
        } else {
           setIsFacingRight(false);
        }
    }
    /**
     * Determine what image should be drawn
     * @param leftInvincible the image of the demon facing to the left that is invincible
     * @param rightInvincible the image of the demon facing to the right that is invincible
     * @param leftNormal the image of the demon facing to the left that is not invincible
     * @param rightNormal the image of the demon facing to the right that is not invincible
     * @return the current image of the demon that should be drawn
     */
    public Image getDemonImage(Image leftInvincible, Image rightInvincible, Image leftNormal, Image rightNormal){
        if (isInvincible()) {
            if (isFacingRight()) {
                return rightInvincible;
            } else {
                return leftInvincible;
            }
        } else {
            if (isFacingRight()){
                return rightNormal;
            } else {
                return leftNormal;
            }
        }
    }
    /**
     * Set the position of the health bar in relation to the current position of the demon
     * @return the position of the top left of the health bar
     */
    public Point setDemonHealthBarPos(){
        Point pos = new Point(getPosition().x, getPosition().y - HEALTH_BAR_Y_OFFSET);
        return pos;
    }
    /**
     * Attempt to attack the player
     */
    @Override
    public void attackLivingEntity(){
        Player player = ShadowDimension.getInstance().getLevelInstance().getPlayer();
        // If the player is not invincible, damage the player, start its invincibility phase and print the attack log
        if (!player.isInvincible()) {
            player.damageLivingEntity(this.getBASE_DAMAGE());
            player.attackLog(this);
            player.goInvincible();
        }
    }
}