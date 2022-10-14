import bagel.*;
/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2022
 *
 * Please enter your name below
 * @author Andrew Walton 1272468
 */
public class ShadowDimension extends AbstractGame {
    /** Game attributes */
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DIMENSION";
    private static final int REFRESH_RATE = 60;
    private static Level levelInstance;
    private static ShadowDimension currentInstance;
    /** Time Scale attributes */
    private int timeScale;
    private double gameSpeed = 1;
    private final double GAME_SPEED_CHANGE_PROPORTION = 0.5;
    private final double MAX_TIME_SCALE = 3;
    private final double MIN_TIME_SCALE = -3;
    private boolean kWasDown = false;
    private boolean lWasDown = false;
    /** Font attributes, not static due to fonts being buggy*/
    private final String FONT_PATH = "res/frostbite.ttf";
    private final int DEFAULT_FONT_SIZE = 75;
    private final Font DEFAULT_FONT = new Font(FONT_PATH, DEFAULT_FONT_SIZE);
    /** Getters for private attributes */
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
    public double getGameSpeed() {
        return gameSpeed;
    }
    public int getTimesScale(){
        return timeScale;
    }
    /** Constructor for the Shadow Dimension game */
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
        // If there is no instance of the game, create one
        if (currentInstance == null) {
            currentInstance = new ShadowDimension();
            levelInstance = new Level0();
        }
        // If there is already a game instance, return that instance
        return currentInstance;
    }
    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed
     * @param input the user input
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        setTimeScale(input);
        levelInstance.runLevel(input);
    }
    /**
     * Increases the level of the game from level 0 to level 1
     */
    public void levelUp(){
        levelInstance = new Level1();
    }
    /**
     * Set the timescale depending on user input
     * @param input user input to change the timescale
     */
    public void setTimeScale(Input input){
        // If K key is pressed decrement timescale
        if (input.isDown(Keys.K) && !kWasDown) {
            kWasDown = true;
            decrementTimeScale();
        }
        // If L key is pressed increment timescale
        if (input.isDown(Keys.L) && !lWasDown) {
            lWasDown = true;
            incrementTimeScale();
        }
        // Set the keyWasDown boolean to false if they are not being pressed
        if (input.isUp(Keys.L)) {
            lWasDown = false;
        }
        if (input.isUp(Keys.K)) {
            kWasDown = false;
        }
    }
    /**
     * Set the game speed, by incrementing or decrementing it, depending on a boolean
     * @param incrementing a boolean to determine if game speed should be increased or decreased
     */
    public void setGameSpeed(boolean incrementing){
        if (incrementing) {
            gameSpeed *= (1 + GAME_SPEED_CHANGE_PROPORTION);
        } else {
            gameSpeed *= GAME_SPEED_CHANGE_PROPORTION;
        }
    }

    /**
     * Increment the timescale, if the increase will not cause timescale to go beyond its bounds
     */
    public void incrementTimeScale(){
        if (timeScale + 1 <= MAX_TIME_SCALE){
            timeScale++;
            setGameSpeed(true);
            // Create and print the log the timescale change
            Log incrementLog = new Log();
            incrementLog.timescaleIncrementLog();
        }
    }
    /**
     * Decrement the timescale, if the decrease will not cause timescale to go beyond its bounds
     */
    public void decrementTimeScale(){
        if (timeScale - 1 >= MIN_TIME_SCALE){
            timeScale--;
            setGameSpeed(false);
            // Create and print the log the timescale change
            Log decrementLog = new Log();
            decrementLog.timescaleDecrementLog();
        }
    }
}