import bagel.*;
import bagel.util.Point;

import java.io.FileReader;
import java.util.Scanner;

public abstract class Level /**implements Drawable*/ {
    private static final String LOSS_MESSAGE = "GAME OVER!";
    private final String START_INSTRUCTION = "PRESS SPACE TO START";
    private final int START_MES_SIZE = 40;
    private final int INTRA_START_MES_GAP = 50;

    // NEED TO CHANGE TO READ CSV
    private Point playerPos = new Point(100, 100);
    private Player player = new Player(playerPos,Player.getPlayerBaseDamage(),Player.getPlayerName(), Player.getPlayerMaxHealth());
    private Point wallPos = new Point(200, 200);
    private Wall wall = new Wall(wallPos, Wall.getWallWidth(), Wall.getWallHeight(),Wall.getWallBaseDamage(), Wall.getWallName());
    private Point sinkholePos = new Point(300, 300);
    private Sinkhole sinkhole = new Sinkhole(sinkholePos, Sinkhole.getSinkholeWidth(), Sinkhole.getSinkholeHeight(), Sinkhole.getSinkholeDamage(), Sinkhole.getSinkholeName());

    private GameState gameState;


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static String getLossMessage(){
        return LOSS_MESSAGE;

    }
    public String getSTART_INSTRUCTION() {
        return START_INSTRUCTION;
    }

    public int getSTART_MES_SIZE() {
        return START_MES_SIZE;
    }

    public int getINTRA_START_MES_GAP() {
        return INTRA_START_MES_GAP;
    }

    public GameState getGameState() {
        return gameState;
    }

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
                drawBackground();
                //NEED TO UPDATE ALL GAME ENTITIES
                player.updatePlayer(input);
                wall.updateWall();
                sinkhole.updateSinkhole();
                break;

        }
        updateGameState(input);
    }

    /** Updates the game state depending on
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
            case LEVEL_RUNNING:


        }
    }

    public void readCSV(int levelNum){
        String CSVpath = "res/level" + levelNum + ".png";

        int identifierIndex = 0;
        int xCoordIndex = 1;
        int yCoordIndex = 2;

        try (Scanner file = new Scanner(new FileReader(CSVpath))){

            
        // Catch an error in the CSV file
        } catch (Exception e){
            e.printStackTrace();
        }

    public abstract void drawStartScreen();
    public abstract void drawBackground();


}
