import bagel.*;
import bagel.util.Point;

public class Level1 extends Level{
    private static final String BACKGROUND_PATH = "res/background1.png";
    private static final Image BACKGROUND_IM = new Image(BACKGROUND_PATH);
    private final String ATTACK_INSTRUCTION = "PRESS A TO ATTACK";
    private final String WIN_INSTRUCTION = "DEFEAT NAVEC TO WIN";
    private final int START_MES_1_X = 350;
    private final int START_MES_1_Y = 350;
    private static final int LEVEL_NUM = 1;


    public Level1(){
        super();
        readCSV(LEVEL_NUM);
        setGameState(GameState.START_SCREEN);
    }

    public void drawStartScreen(){
        Font START_MES_FONT = new Font(ShadowDimension.getInstance().getFontPath(), getSTART_MES_SIZE());

        START_MES_FONT.drawString(getSTART_INSTRUCTION(), START_MES_1_X, START_MES_1_Y);
        START_MES_FONT.drawString(ATTACK_INSTRUCTION, ShadowDimension.getWindowWidth()/2 -
                                START_MES_FONT.getWidth(ATTACK_INSTRUCTION)/2,
                             START_MES_1_Y + getINTRA_START_MES_GAP());
        START_MES_FONT.drawString(WIN_INSTRUCTION,ShadowDimension.getWindowWidth()/2 -
                START_MES_FONT.getWidth(WIN_INSTRUCTION)/2,START_MES_1_Y + 2 * getINTRA_START_MES_GAP());


    }
    public void drawBackground(){
        BACKGROUND_IM.draw(ShadowDimension.getWindowWidth()/2,ShadowDimension.getWindowHeight()/2);
    }
    public boolean hasBeatLevel(){
        return false;
    }
    public void drawLevelWinScreen(){
    }
}
