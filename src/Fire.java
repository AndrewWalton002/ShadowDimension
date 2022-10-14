import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Rectangle;
import java.lang.Math;
/** A representation of the fire that demons can shoot at the player */
public class Fire extends Rectangle {
    /** Image attributes */
    private static final String DEMON_FIRE_PATH = "res/demon/demonFire.png";
    private static final Image DEMON_FIRE_IMAGE = new Image(DEMON_FIRE_PATH);
    private static final int DEMON_FIRE_WIDTH = 33;
    private static final int DEMON_FIRE_HEIGHT = 34;
    private static final String NAVEC_FIRE_PATH = "res/navec/navecFire.png";
    private static final Image NAVEC_FIRE_IMAGE = new Image(NAVEC_FIRE_PATH);
    private static final int NAVEC_FIRE_WIDTH = 54;
    private static final int NAVEC_FIRE_HEIGHT = 45;
    private final Image FIRE_IMAGE;
    private final int FIRE_WIDTH;
    private final int FIRE_HEIGHT;
    /** Player related attributes */
    private boolean hasHitPlayer = false;
    private Player player;
    /** The demon that shoots the fire */
    private Demon demon;
    /** The rotation attributes for the fire */
    private static final double BOTTOM_LEFT_ROTATION = (3 * Math.PI) / 2;
    private static final double TOP_RIGHT_ROTATION = (Math.PI / 2);
    private static final double BOTTOM_RIGHT_ROTATION = Math.PI;
    /** Create a fire that a demon shoots */
    public Fire(Demon demon, Player player) {
        super(demon.topLeft().x, demon.topLeft().y, DEMON_FIRE_WIDTH, DEMON_FIRE_HEIGHT);
        this.demon = demon;
        this.player = player;
        FIRE_IMAGE = DEMON_FIRE_IMAGE;
        FIRE_WIDTH = DEMON_FIRE_WIDTH;
        FIRE_HEIGHT = DEMON_FIRE_HEIGHT;
    }
    /** Create a fire that Navec shoots */
    public Fire(Navec navec, Player player){
        super(navec.topLeft().x, navec.topLeft().y, NAVEC_FIRE_WIDTH, NAVEC_FIRE_HEIGHT);
        this.demon = navec;
        this.player = player;
        FIRE_IMAGE = NAVEC_FIRE_IMAGE;
        FIRE_WIDTH = NAVEC_FIRE_WIDTH;
        FIRE_HEIGHT = NAVEC_FIRE_HEIGHT;
    }
    /**
     * Update the fire
     */
    public void updateFire(){
        this.player = ShadowDimension.getInstance().getLevelInstance().getPlayer();
        // If the player is in the demon's range draw the fire
        if (playerInRange()) {
            drawFire(FIRE_IMAGE, FIRE_WIDTH, FIRE_HEIGHT);
            // If the player is colliding with the fire, attack them
            if (fireHittingPlayer()){
                demon.attackLivingEntity();
            }
        }
    }
    /**
     * Draw the fire in the correct orientation
     * @param fireImage the image of fire to be drawn
     * @param width the width of the image of the fire
     * @param height the height of the image of the fire
     */
    public void drawFire(Image fireImage, int width, int height){
        DrawOptions rotation = new DrawOptions();
        // Draw the fire at the correct corner of the demon with the appropriate orientation
        if (player.centre().x <= demon.centre().x && player.centre().y <= demon.centre().y){
            fireImage.draw(demon.topLeft().x - width / 2,
                                demon.topLeft().y - height / 2);
        } else if (player.centre().x <= demon.centre().x && player.centre().y > demon.centre().y){
            fireImage.draw(demon.bottomLeft().x - width / 2,
                                 demon.bottomLeft().y + height / 2,
                                    rotation.setRotation(BOTTOM_LEFT_ROTATION));
        } else if (player.centre().x > demon.centre().x && player.centre().y <= demon.centre().y){
            fireImage.draw(demon.topRight().x + width / 2,
                                 demon.topRight().y - height / 2,
                                    rotation.setRotation(TOP_RIGHT_ROTATION));
        } else if (player.centre().x > demon.centre().x && player.centre().y > demon.centre().y){
            fireImage.draw(demon.bottomRight().x + width / 2,
                                 demon.bottomRight().y + height / 2,
                                    rotation.setRotation(BOTTOM_RIGHT_ROTATION));
        }
    }
    /**
     * Determine if the player is in the demon's range
     * @return a boolean of if the player is in range
     */
    public boolean playerInRange() {
        return player.centre().distanceTo(demon.centre()) <= demon.getFireRange();
    }
    /**
     * Determine if the player is colliding with the fire
     * @return a boolean of if the player collides with the fire
     */
    public boolean fireHittingPlayer(){
        return this.intersects(player);
    }
}