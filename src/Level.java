import bagel.*;
public abstract class Level /**implements Drawable*/ {
    private static final String LOSS_MESSAGE = "GAME OVER!";
    protected final String START_INSTRUCTION = "PRESS SPACE TO START";
    protected final int START_MES_SIZE = 40;
    protected final int INTRA_START_MES_GAP = 50;
    //protected final Font START_MES_FONT = new Font(ShadowDimension.getInstance().getFontPath(), START_MES_SIZE);;

    protected GameState gameState;

    public Level(){
    }

    public void runLevel(Input input){
        switch (gameState){
            case START_SCREEN:
                drawStartScreen();
        }
    }

    public abstract void drawStartScreen();


}
