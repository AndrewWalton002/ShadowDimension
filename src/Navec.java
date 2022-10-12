import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class Navec extends AggressiveDemon{
    private static final String NAVEC_LEFT_PATH = "res/navec/navecLeft.png";
    private static final Image NAVEC_LEFT_IMAGE = new Image(NAVEC_LEFT_PATH);
    private static final String NAVEC_RIGHT_PATH = "res/navec/navecRight.png";
    private static final Image NAVEC_RIGHT_IMAGE = new Image(NAVEC_RIGHT_PATH);
    private static final String NAVEC_INVINCIBLE_LEFT_PATH = "res/navec/navecInvincibleLeft.PNG";
    private static final Image NAVEC_INVINCIBLE_LEFT_IMAGE = new Image(NAVEC_INVINCIBLE_LEFT_PATH);
    private static final String NAVEC_INVINCIBLE_RIGHT_PATH = "res/navec/navecInvincibleRight.PNG";
    private static final Image NAVEC_INVINCIBLE_RIGHT_IMAGE = new Image(NAVEC_INVINCIBLE_RIGHT_PATH);
    private static final int NAVEC_WIDTH = 61;
    private static final int NAVEC_HEIGHT = 61;
    private static final String NAVEC_NAME = "Navec";
    private static final int NAVEC_MULTIPLIER = 2;
    private static final int NAVEC_DAMAGE = NAVEC_MULTIPLIER * Demon.getDemonDamage();
    private static final int NAVEC_MAX_HEALTH = NAVEC_MULTIPLIER * Demon.getDemonMaxHealth();
    private static final int NAVEC_FIRE_RANGE = 200;
    Fire NAVEC_FIRE = new Fire(this, ShadowDimension.getInstance().getLevelInstance().getPlayer());


    @Override
    public int getFireRange(){
        return NAVEC_FIRE_RANGE;
    }




    public static int getNavecWidth(){
        return NAVEC_WIDTH;
    }
    public static int getNavecHeight(){
        return NAVEC_HEIGHT;
    }
    public static String getNavecName(){
        return NAVEC_NAME;
    }
    public static int getNavecDamage(){
        return NAVEC_DAMAGE;
    }
    public static int getNavecMaxHealth(){
        return NAVEC_MAX_HEALTH;
    }
    public Navec(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health);
        setFire(NAVEC_FIRE);
    }

    public Image getLeftImage(){
        return NAVEC_LEFT_IMAGE;
    }
    public Image getRightImage(){
        return NAVEC_RIGHT_IMAGE;
    }
    public Image getInvincibleLeftImage(){
        return NAVEC_INVINCIBLE_LEFT_IMAGE;
    }
    public Image getInvincibleRightImage(){
        return NAVEC_INVINCIBLE_RIGHT_IMAGE;
    }


    @Override
    public void updateGameEntity(Input input){
        super.updateGameEntity(input);
    }
}

