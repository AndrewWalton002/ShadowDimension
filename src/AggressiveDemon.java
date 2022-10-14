import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
/** The class that represents the additional features of an aggressive demon */
public class AggressiveDemon extends Demon implements Moveable{
    /** Movement speed attributes */
    private static final double MIN_AGG_DEMON_SPEED = 0.2;
    private static final double MAX_AGG_DEMON_SPEED = 0.7;
    private final double ORIGINAL_AGG_DEMON_SPEED = (Math.random() * (MAX_AGG_DEMON_SPEED - MIN_AGG_DEMON_SPEED + 1) *
                                            MIN_AGG_DEMON_SPEED) ;
    /** Movement direction attributes */
    private DemonDirection demonDirection;
    private static final int NUM_DIRECTIONS = 4;
    private static final int MIN_NUM_DIRECTIONS = 1;
    private static final int NORTH_INT = 0;
    private static final int EAST_INT = 1;
    private static final int SOUTH_INT = 2;
    private static final int WEST_INT = 3;
    /** Constructor for an aggressive demon */
    public AggressiveDemon(Point position, int width, int height, double BASE_DAMAGE, String name, double max_health) {
        super(position, width, height, BASE_DAMAGE, name, max_health);
        setMovementSpeed(ORIGINAL_AGG_DEMON_SPEED * ShadowDimension.getInstance().getGameSpeed());
        setDemonDirection();
    }
    /**
     * Update the aggressive demon, by moving the aggressive demon
     * @param input user input
     */
    @Override
    public void updateGameEntity(Input input){
        setMovementSpeed(ORIGINAL_AGG_DEMON_SPEED * ShadowDimension.getInstance().getGameSpeed());
        Point newPos = getNewPosition();
        tryMove(newPos);
        super.updateGameEntity(input);
    }
    /**
     * Set a random direction for the aggressive demon to start moving in
     */
    public void setDemonDirection(){
        int randomDirectionInt = (int)(Math.random() * (NUM_DIRECTIONS - MIN_NUM_DIRECTIONS + 1) * MIN_NUM_DIRECTIONS);
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
    /**
     * Make the aggressive demon change direction to move in the opposite direction, and update which way they are
     * facing if that is necessary
     */
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
    /**
     * Find the position that the aggressive demon will try to move to
     * @return the new position of the aggressive demon
     */
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
    /**
     * Move the aggressive demon to its next position if the new position does not make the aggressive demon go out of
     * the level bounds or collide with a stationary object
     * @param newPos the new position of the aggressive demon
     */
    @Override
    public void tryMove(Point newPos) {
        Rectangle collidingObject = collidingWithRectangle(newPos);
        if (collidingObject == null) {
            setPosition(newPos);
        // If the demon is going to collide with a stationary object, change the demon's direction
        } else {
            changeDemonDirection();
        }
    }
}