import bagel.*;
/** Encapsulates all the logic for level 0 */
public class Level0 extends Level {
    private static final int LEVEL_NUM = 0;
    /** Messaging attributes */
    private final int TITLE_MES_X = 260;
    private final int TITLE_MES_Y = 250;
    private final int WIN_X_COORD = 950;
    private final int WIN_Y_COORD = 670;
    private final String MOVE_INSTRUCTION = "USE ARROW KEYS TO FIND GATES";
    private final String WIN_MESSAGE = "LEVEL COMPLETE!";

    private final int START_MES_GAP_X = 90;
    private final int START_MES_GAP_Y = 190;
    /** Background image attributes */
    private static final  String BACKGROUND_STR = "res/background0.png";
    private static final Image BACKGROUND_IMAGE = new Image(BACKGROUND_STR);
    /** Create an instance of level 0 */
    public Level0(){
        readCSV(LEVEL_NUM);
        setGameState(GameState.START_SCREEN);
    }
    /**
     * Draw the background image of the level
     */
    public void drawBackground(){
        BACKGROUND_IMAGE.draw(ShadowDimension.getWindowWidth()/2,ShadowDimension.getWindowHeight()/2);
    }
    /**
     * Draw the level start screen
     */
    public void drawStartScreen(){
        Font START_MES_FONT = new Font(ShadowDimension.getInstance().getFontPath(), getSTART_MES_SIZE());
        // Draw the title string
        ShadowDimension.getInstance().getDefaultFont().drawString(
                            ShadowDimension.getGameTitle(),TITLE_MES_X, TITLE_MES_Y);
        // Draw start messages
        START_MES_FONT.drawString(getSTART_INSTRUCTION(), TITLE_MES_X + START_MES_GAP_X,
                                                        TITLE_MES_Y + START_MES_GAP_Y);
        START_MES_FONT.drawString(MOVE_INSTRUCTION, ShadowDimension.getWindowWidth() / 2 -
                                START_MES_FONT.getWidth(MOVE_INSTRUCTION) / 2,
                             TITLE_MES_Y + START_MES_GAP_Y + getINTRA_START_MES_GAP());
    }
    /**
     * Run the level 0 specific game mechanics depending on the game state
     * @param input user input
     */
    @Override
    public void runLevel(Input input){
        super.runLevel(input);
        if(getGameState() == GameState.LEVEL_WON){
            // Draw the win screen for the specified amount of time
            if (getFrameCounter() < getWIN_SCREEN_FRAMES()){
                drawLevelWinScreen();
                setFrameCounter(getFrameCounter() + 1);
            }
        }
    }
    /**
     * Update the game state and level depending on the game state
     * @param input user input
     */
    @Override
    public void updateGameState(Input input){
        super.updateGameState(input);
        if (getGameState() == GameState.LEVEL_WON){
            // If the winning screen has been displayed for the specified amount of the time, level up the game
            if (getFrameCounter() == getWIN_SCREEN_FRAMES()){
                ShadowDimension.getInstance().levelUp();
            }
        }
    }
    /**
     * Determine if the player has beaten the level
     * @return true if the level has been beaten, and false if it has not
     */
    @Override
    public boolean hasBeatLevel(){
        return getPlayer().topLeft().x >= WIN_X_COORD && getPlayer().topLeft().y >= WIN_Y_COORD;
    }
    /**
     * Draw the winning screen for level 0
     */
    @Override
    public void drawLevelWinScreen(){
        Font defaultFont = ShadowDimension.getInstance().getDefaultFont();
        defaultFont.drawString(WIN_MESSAGE, ShadowDimension.getWindowWidth()/2 -
                defaultFont.getWidth(WIN_MESSAGE)/2, ShadowDimension.getWindowHeight()/2 -
                ShadowDimension.getInstance().getDEFAULT_FONT_SIZE()/2);
    }
}