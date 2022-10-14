import bagel.Input;
import bagel.util.Point;
/** An abstract class that represents all stationary objects that the player cannot pass through */
public abstract class StationaryEntity extends GameEntity{
    /** Constructor for a stationary entity */
    public StationaryEntity(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }
    public abstract void updateGameEntity(Input input);
}