import bagel.*;
import bagel.util.*;

public abstract class GameEntity extends Rectangle implements Drawable {
    private Point position;
    private int width;
    private int height;

    private final double BASE_DAMAGE;

    public GameEntity(Point position, int width, int height, double BASE_DAMAGE){
        super(position.x, position.y, width, height);
        this.position = position;
        this.width = width;
        this.height = height;
        this.BASE_DAMAGE = BASE_DAMAGE;
    }

}
