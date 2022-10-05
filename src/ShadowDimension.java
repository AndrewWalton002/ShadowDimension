import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2022
 *
 * Please enter your name below
 * @author
 */

public class ShadowDimension extends AbstractGame {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DIMENSION";

    /** Time Scale attributes */
    private double timeScale;
    private final double TIME_SCALE_CHANGE_PERCENT = 50;
    private final double MAX_TIME_SCALE = 3;
    private final double MIN_TIME_SCALE = -3;

    /** Font attributes */
    private static final String FONT_PATH = "res/frostbite.ttf";
    private static final int DEFAULT_FONT_SIZE = 75;
    private static final Font DEFAULT_FONT = new Font(FONT_PATH, DEFAULT_FONT_SIZE);

    private static final int REFRESH_RATE = 60;



    public ShadowDimension(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDimension game = new ShadowDimension();
        game.run();
    }

    /**
     * Method used to read file and create objects (You can change this
     * method as you wish).
     */
    private void readCSV(){

    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

    }
}
