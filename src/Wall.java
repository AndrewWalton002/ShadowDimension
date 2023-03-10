import bagel.Image;
import bagel.Input;
import bagel.util.Point;
/** A class encapsulation all the information regarding walls */
public class Wall extends StationaryEntity{
    private static final int WALL_BASE_DAMAGE = 0;
    private static final String WALL_NAME = "Wall";
    /** Image attributes */
    private static final String WALL_PATH = "res/wall.png";
    private static final Image WALL_IM = new Image(WALL_PATH);
    private static final int WIDTH = 58;
    private static final int HEIGHT = 66;
    /** Getters */
    public static int getWallWidth(){
        return WIDTH;
    }
    public static int getWallHeight(){
        return HEIGHT;
    }
    public static int getWallBaseDamage(){
        return WALL_BASE_DAMAGE;
    }
    public static String getWallName(){
        return WALL_NAME;
    }
    /** Constructor for a wall */
    public Wall(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }
    /** Draw a wall */
    @Override
    public void updateGameEntity(Input input) {
        drawGameEntity(WALL_IM);
    }
}