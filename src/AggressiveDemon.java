import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class AggressiveDemon extends Demon{


    public AggressiveDemon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health);
    }

    @Override
    public void updateGameEntity(Input input){
    }


}
