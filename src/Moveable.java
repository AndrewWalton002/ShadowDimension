import bagel.util.Point;
/** An interface for all entities that can move */
public interface Moveable{
    /**
     * Checks if a move to a new position is allowed, and move the moveable entity if the move is valid
     * @param newPos the position to which the moveable entity is trying to move to
     */
    void tryMove(Point newPos);
}