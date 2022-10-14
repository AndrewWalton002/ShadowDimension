import bagel.*;
/** Encapsulates all the logic for level 1 */
public class Level1 extends Level {
    private static final int LEVEL_NUM = 1;
    /** Background image attributes */
    private static final String BACKGROUND_PATH = "res/background1.png";
    private static final Image BACKGROUND_IM = new Image(BACKGROUND_PATH);
    /** Message attributes */
    private final String ATTACK_INSTRUCTION = "PRESS A TO ATTACK";
    private final String WIN_INSTRUCTION = "DEFEAT NAVEC TO WIN";
    private final String WIN_MESSAGE = "CONGRATULATIONS!";
    private final int START_MES_1_X = 350;
    private final int START_MES_1_Y = 350;
    public Level1() {
        readCSV(LEVEL_NUM);
        setGameState(GameState.START_SCREEN);
    }
    /**
     * Run the level 1 specific game mechanics depending on the game state
     * @param input user input
     */
    @Override
    public void runLevel(Input input) {
        super.runLevel(input);
        if (getGameState() == GameState.LEVEL_WON) {
            drawLevelWinScreen();
        }
    }
    /**
     * Update the game state and level depending on the game state
     * @param input user input
     */
    @Override
    public void updateGameState(Input input) {
        super.updateGameState(input);
        if (hasBeatLevel()) {
            setGameState(GameState.LEVEL_WON);
        }
    }
    /**
     * Draw the level start screen
     */
    public void drawStartScreen() {
        Font START_MES_FONT = new Font(ShadowDimension.getInstance().getFontPath(), getSTART_MES_SIZE());
        // Draw the level start messages
        START_MES_FONT.drawString(getSTART_INSTRUCTION(), START_MES_1_X, START_MES_1_Y);
        START_MES_FONT.drawString(ATTACK_INSTRUCTION, ShadowDimension.getWindowWidth() / 2 -
                        START_MES_FONT.getWidth(ATTACK_INSTRUCTION) / 2,
                START_MES_1_Y + getINTRA_START_MES_GAP());
        START_MES_FONT.drawString(WIN_INSTRUCTION, ShadowDimension.getWindowWidth() / 2 -
                START_MES_FONT.getWidth(WIN_INSTRUCTION) / 2, START_MES_1_Y + 2 * getINTRA_START_MES_GAP());
    }
    /**
     * Draw the background image for level 1
     */
    public void drawBackground() {
        BACKGROUND_IM.draw(ShadowDimension.getWindowWidth() / 2, ShadowDimension.getWindowHeight() / 2);
    }
    /**
     * Determine if the player has beaten the level
     * @return true if the level has been beaten, and false if it has not
     */
    public boolean hasBeatLevel() {
        for (int i = 0; i < getGameEntities().size(); i++) {
            // If Navec has not been killed and removed from the game entities, the level has not been won
            if (getGameEntities().get(i) instanceof Navec) {
                return false;
            }
        }
        // If Navec has been killed and removed from game entities, the level has been won
        return true;
    }
    /**
     * Draw the winning screen for level 1
     */
    public void drawLevelWinScreen(){
        Font defaultFont = ShadowDimension.getInstance().getDefaultFont();
        defaultFont.drawString(WIN_MESSAGE, ShadowDimension.getWindowWidth() / 2 -
                defaultFont.getWidth(WIN_MESSAGE) / 2, ShadowDimension.getWindowHeight() / 2 -
                ShadowDimension.getInstance().getDEFAULT_FONT_SIZE() / 2);
    }
}