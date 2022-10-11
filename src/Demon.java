import bagel.Image;
import bagel.Input;
import bagel.util.Point;

import java.util.Random;

public class Demon extends LivingEntity{
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
    private boolean isInvincible = false;
    private static final String DEMON_NAME = "Demon";
    private static final int DEMON_HEALTH_BAR_FONT_SIZE = 15;
    private static int DEMON_DAMAGE = 10;
    private static int DEMON_MAX_HEALTH = 40;
    private static int DEMON_SPEED = 0;


    public static int getDemonMaxHealth(){
        return DEMON_DAMAGE;
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


    public Demon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health, DEMON_HEALTH_BAR_FONT_SIZE);
        setMovementSpeed(DEMON_SPEED);
        setDemonFacingDirection();
    }

    @Override
    public void updateGameEntity(Input input) {
        drawGameEntity(getDemonImage(getInvincibleLeftImage(), getInvincibleRightImage(), getLeftImage(),
                                    getRightImage()));
    }


    public void setDemonFacingDirection(){
        Random rand = new Random();
        if (rand.nextInt() % 2 != 0) {
            setIsFacingRight(true);
        } else {
           setIsFacingRight(false);
        }
    }

    public Image getDemonImage(Image leftInvincible, Image rightInvincible, Image leftNormal, Image rightNormal){
        if (isInvincible) {
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

}
