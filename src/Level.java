import bagel.*;
public abstract class Level /**implements Drawable*/ {
    private static final String LOSS_MESSAGE = "GAME OVER!";
    protected final String START_INSTRUCTION = "PRESS SPACE TO START";
    protected final int START_MES_SIZE = 40;
    protected final int INTRA_START_MES_GAP = 50;

    protected GameState gameState;

    public Level(){
    }

    /** Runs the level depending on the current game state
     *
     * @param input user input
     */
    public void runLevel(Input input){
        switch (gameState){
            case START_SCREEN:
                drawStartScreen();
                break;
            case LEVEL_RUNNING:
                break;

        }
        updateGameState(input);
    }

    /** Updates the game state depending
     *
     * @param input user input
     */
    public void updateGameState(Input input){
        switch (gameState){
            case START_SCREEN:
                // Game is started by pressing space
                if (input.wasPressed(Keys.SPACE)) {
                    gameState = GameState.LEVEL_RUNNING;
                }
                break;
        }
    }

    public abstract void drawStartScreen();


}
