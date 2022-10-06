import bagel.util.Point;

public abstract class StationaryEntity extends GameEntity{
    public StationaryEntity(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }
}
