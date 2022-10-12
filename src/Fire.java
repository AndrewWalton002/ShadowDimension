import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.lang.Math;

public class Fire extends Rectangle {
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
    private Player player;
    private Demon demon;
    private static final double BOTTOM_LEFT_ROTATION = (3 * Math.PI) / 2;
    private static final double TOP_RIGHT_ROTATION = (Math.PI / 2);
    private static final double BOTTOM_RIGHT_ROTATION = Math.PI;
    private boolean hasHitPlayer = false;

    public static int getFireWidth(){
        return DEMON_FIRE_WIDTH;
    }
    public static int getFireHeight(){
        return DEMON_FIRE_HEIGHT;
    }


    public Fire(Demon demon, Player player) {
        super(demon.topLeft().x, demon.topLeft().y, DEMON_FIRE_WIDTH, DEMON_FIRE_HEIGHT);
        this.demon = demon;
        this.player = player;
        FIRE_IMAGE = DEMON_FIRE_IMAGE;
        FIRE_WIDTH = DEMON_FIRE_WIDTH;
        FIRE_HEIGHT = DEMON_FIRE_HEIGHT;
    }

    public Fire(Navec navec, Player player){
        super(navec.topLeft().x, navec.topLeft().y, NAVEC_FIRE_WIDTH, NAVEC_FIRE_HEIGHT);
        this.demon = navec;
        this.player = player;
        FIRE_IMAGE = NAVEC_FIRE_IMAGE;
        FIRE_WIDTH = NAVEC_FIRE_WIDTH;
        FIRE_HEIGHT = NAVEC_FIRE_HEIGHT;
    }

    public void updateFire(){
        this.player = ShadowDimension.getInstance().getLevelInstance().getPlayer();

        if (playerInRange()) {
            drawFire(FIRE_IMAGE, FIRE_WIDTH, FIRE_HEIGHT);
            if (fireHittingPlayer()){
                demon.attackLivingEntity();
            }
        }
    }

    public void drawFire(Image fireImage, int width, int height){

        DrawOptions rotation = new DrawOptions();

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

    public boolean playerInRange() {
        return player.centre().distanceTo(demon.centre()) <= demon.getFireRange();
    }

    public boolean fireHittingPlayer(){
        return this.intersects(player);
    }

}
