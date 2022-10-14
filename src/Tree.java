import bagel.Image;
import bagel.Input;
import bagel.util.Point;
/** A class encapsulation all the information regarding trees */
public class Tree extends StationaryEntity{
    private static final String TREE_NAME = "Tree";
    private static final int TREE_DAMAGE = 0;
    /** Image attributes */
    private static final String TREE_PATH = "res/tree.png";
    private static final Image TREE_IMAGE = new Image(TREE_PATH);
    private static final int TREE_WIDTH = 59;
    private static final int TREE_HEIGHT = 63;
    /** Getters */
    public static int getTreeDamage(){
        return TREE_DAMAGE;
    }
    public static String getTreeName(){
        return TREE_NAME;
    }
    public static int getTreeWidth(){
        return TREE_WIDTH;
    }
    public static int getTreeHeight(){
        return TREE_HEIGHT;
    }
    /** Constructor for a tree */
    public Tree(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }
    /** Draw a tree */
    @Override
    public void updateGameEntity(Input input) {
        drawGameEntity(TREE_IMAGE);
    }
}
