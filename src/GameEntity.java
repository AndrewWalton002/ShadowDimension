import bagel.*;
import bagel.util.*;
import java.util.ArrayList;

/**
 * An abstract class that all objects that interact with each other in the game, that are comparable
 */
public abstract class GameEntity extends Rectangle implements Comparable<GameEntity>{
    private Point position;
    private int width;
    private int height;
    private final double BASE_DAMAGE;
    private final String NAME;
    /** Getters and setters */
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public double getBASE_DAMAGE() {
        return BASE_DAMAGE;
    }
    public String getNAME() {
        return NAME;
    }
    /** Constructor for a game entity */
    public GameEntity(Point position, int width, int height, double BASE_DAMAGE, String NAME){
        super(position.x, position.y, width, height);
        this.position = position;
        this.width = width;
        this.height = height;
        this.BASE_DAMAGE = BASE_DAMAGE;
        this.NAME = NAME;
    }
    /**
     * Draw a provided game entity
     * @param image an image of a game entity
     */
    public void drawGameEntity(Image image){
        image.drawFromTopLeft(position.x, position.y);
    }
    /**
     * Override the compareTo method from the comparable interface
     * @param o the object to be compared.
     * @return less than 0, if o is greater than the original game entity. 0 if they are the same. Greater than 0 if
     * o is less than the original game entity
     */
    @Override
    public int compareTo(GameEntity o){
        if (this.NAME.compareTo(o.NAME) == 0 && this.position.x == o.position.x && this.position.y == o.position.y){
            return 0;
        } else if (this.position.x != o.position.x){
            return (int)(this.position.x - o.position.x);
        } else if (this.position.y != o.position.y) {
            return (int) (this.position.y - o.position.y);
        } else {
            return this.NAME.compareTo(o.NAME);
        }
    }
    /**
     * Remove the game entity that calls the method
     */
    public void removeGameEntity(){
        ArrayList<GameEntity> gameEntities = ShadowDimension.getInstance().getLevelInstance().getGameEntities();
        for (int i = 0; i < gameEntities.size(); i++){
            // If the game entity is the same as the game entity that called the method, remove that game entity
            if (gameEntities.get(i).compareTo(this) == 0){
                gameEntities.remove(i);
                return;
            }

        }
    }
    public abstract void updateGameEntity(Input input);
}
