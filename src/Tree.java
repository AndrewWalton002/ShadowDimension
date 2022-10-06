import bagel.Input;
import bagel.util.Point;

public class Tree extends StationaryEntity{
    public Tree(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }

    @Override
    public void updateGameEntity(Input input) {

    }
}
