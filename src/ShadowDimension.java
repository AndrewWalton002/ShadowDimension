import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2022
 *
 * Please enter your name below
 * @author Andrew Walton 1272468
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

    /** Font attributes, not static due to fonts being buggy*/
    private final String FONT_PATH = "res/frostbite.ttf";
    private final int DEFAULT_FONT_SIZE = 75;
    private final Font DEFAULT_FONT = new Font(FONT_PATH, DEFAULT_FONT_SIZE);

    private static final int REFRESH_RATE = 60;
    private static Level levelInstance;
    private Levels currentLevel = Levels.LEVEL_0;

    private static ShadowDimension currentInstance;

    public Level getLevelInstance() {
        return levelInstance;
    }

    public static int getRefreshRate(){
        return REFRESH_RATE;
    }
    public static int getWindowWidth(){
        return WINDOW_WIDTH;
    }
    public static int getWindowHeight(){
        return WINDOW_HEIGHT;
    }
    public static String getGameTitle(){
        return GAME_TITLE;
    }
    public String getFontPath(){
        return FONT_PATH;
    }
    public Font getDefaultFont(){
        return DEFAULT_FONT;
    }

    public int getDEFAULT_FONT_SIZE() {
        return DEFAULT_FONT_SIZE;
    }

    public ShadowDimension(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDimension game = getInstance();
        game.run();
    }


    /** Ensures there is only one instance of the game and gets that game instance
     * @return ShadowDimension the current instance of the game
     */
    public static ShadowDimension getInstance() {
        if (currentInstance == null) {
            currentInstance = new ShadowDimension();
            levelInstance = new Level0();

        }
        return currentInstance;
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.4
     * @param input the user input
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        levelInstance.runLevel(input);

    }

    public void levelUp(){
        levelInstance = new Level1();
        currentLevel = Levels.LEVEL_1;
    }


}
