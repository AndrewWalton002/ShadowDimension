import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

public class AggressiveDemon extends Demon{
    private static final double MIN_AGG_DEMON_SPEED = 0.2;
    private static final double MAX_AGG_DEMON_SPEED = 0.7;
    private final double AGG_DEMON_SPEED = (Math.random() * (MAX_AGG_DEMON_SPEED - MIN_AGG_DEMON_SPEED + 1) *
                                                            MIN_AGG_DEMON_SPEED);
    private DemonDirection demonDirection;
    private static final int NUM_DIRECTIONS = 4;
    private static final int MIN_NUM_DIRECTIONS = 1;
    private static final int NORTH_INT = 0;
    private static final int EAST_INT = 1;
    private static final int SOUTH_INT = 2;
    private static final int WEST_INT = 3;


    public AggressiveDemon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health);
        setMovementSpeed(AGG_DEMON_SPEED);
        setDemonDirection();
    }

    @Override
    public void updateGameEntity(Input input){

        Point newPos = getNewPosition();
        Rectangle collidingObject = collidingWithRectangle(newPos);
        if (collidingObject == null) {
            setPosition(newPos);
        } else {
            changeDemonDirection();
        }
        super.updateGameEntity(input);



    }

    public void setDemonDirection(){

        int randomDirectionInt = (int)(Math.random() * (NUM_DIRECTIONS - MIN_NUM_DIRECTIONS + 1) * MIN_NUM_DIRECTIONS);
        System.out.println(randomDirectionInt);

        if (randomDirectionInt == NORTH_INT){
            demonDirection = DemonDirection.North;
        } else if (randomDirectionInt == EAST_INT){
            demonDirection = DemonDirection.East;
            setIsFacingRight(true);
        } else if (randomDirectionInt == SOUTH_INT){
            demonDirection = DemonDirection.South;
        } else if (randomDirectionInt == WEST_INT){
            demonDirection = DemonDirection.West;
            setIsFacingRight(false);
        }
    }

    public void changeDemonDirection(){
        if (demonDirection == DemonDirection.North){
            demonDirection = DemonDirection.South;
        } else if (demonDirection == DemonDirection.East){
            setIsFacingRight(false);
            demonDirection = DemonDirection.West;
        } else if (demonDirection == DemonDirection.South){
            demonDirection = DemonDirection.North;
        } else if (demonDirection == DemonDirection.West){
            demonDirection = DemonDirection.East;
            setIsFacingRight(true);
        }
    }

    public Point getNewPosition(){
        Point newPos = getPosition();
        if (demonDirection == DemonDirection.North){
            newPos = new Point(getPosition().x, getPosition().y - getMovementSpeed());
        } else if (demonDirection == DemonDirection.East){
            newPos = new Point(getPosition().x + getMovementSpeed(), getPosition().y);
        } else if (demonDirection == DemonDirection.South){
            newPos = new Point(getPosition().x, getPosition().y + getMovementSpeed());
        } else if (demonDirection == DemonDirection.West){
            newPos = new Point(getPosition().x - getMovementSpeed(), getPosition().y);
        }
        return newPos;
    }





}
