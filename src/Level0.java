import bagel.*;
import bagel.util.Point;

public class Level0 extends Level {
    private final int TITLE_MES_X = 260;
    private final int TITLE_MES_Y = 250;

    private final String MOVE_INSTRUCTION = "USE ARROW KEYS TO FIND GATES";

    private final int START_MES_GAP_X = 90;
    private final int START_MES_GAP_Y = 190;


    public Level0(){
        super();
        gameState = GameState.START_SCREEN;
    }

    public void drawStartScreen(){

        Font START_MES_FONT = new Font(ShadowDimension.getInstance().getFontPath(), START_MES_SIZE);

        // Draw the title string
        ShadowDimension.getInstance().getDefaultFont().drawString(
                            ShadowDimension.getGameTitle(),TITLE_MES_X, TITLE_MES_Y);

        // Draw start messages
        START_MES_FONT.drawString(START_INSTRUCTION, TITLE_MES_X + START_MES_GAP_X,
                                                        TITLE_MES_Y + START_MES_GAP_Y);
        START_MES_FONT.drawString(MOVE_INSTRUCTION, ShadowDimension.getWindowWidth() / 2 -
                                START_MES_FONT.getWidth(MOVE_INSTRUCTION) / 2,
                             TITLE_MES_Y + START_MES_GAP_Y + INTRA_START_MES_GAP);


    }
}
