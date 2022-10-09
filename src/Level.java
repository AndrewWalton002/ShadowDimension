import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Level /**implements Drawable*/ {
    private static final String LOSS_MESSAGE = "GAME OVER!";
    private final String START_INSTRUCTION = "PRESS SPACE TO START";
    private final int START_MES_SIZE = 40;
    private final int INTRA_START_MES_GAP = 50;
    private final String TOP_LEFT_INDICATOR = "TopLeft";
    private final String BOTTOM_RIGHT_INDICATOR = "BottomRight";

    private ArrayList<GameEntity> gameEntities = new ArrayList<GameEntity>();
    private Player player;
    private Rectangle levelBounds;

    private GameState gameState;
    private static Level levelInstance;
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public ArrayList<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public Rectangle getLevelBounds() {
        return levelBounds;
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

                player.updateGameEntity(input);
                gameEntities.forEach((gameEntity -> gameEntity.updateGameEntity(input)));


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

    public void readCSV(int levelNum) {
        String CSVpath = "res/level" + levelNum + ".csv";

        int identifierIndex = 0;
        int xCoordIndex = 1;
        int yCoordIndex = 2;


        try (Scanner file = new Scanner(new FileReader(CSVpath))) {

            Point topLeft = null;
            Point bottomRight = null;

            while (file.hasNextLine()) {

                String[] currentLine = file.nextLine().split(",");

                Point objectPosition = new Point(Double.parseDouble(currentLine[xCoordIndex]),
                        Double.parseDouble(currentLine[yCoordIndex]));

                // Create the player at the defined position
                if (currentLine[identifierIndex].compareTo(Player.getPlayerName()) == 0){
                    player = new Player(objectPosition, Player.getPlayerBaseDamage(),
                                        Player.getPlayerName(), Player.getPlayerMaxHealth());
                }

                // Add a wall at the defined position
                else if (currentLine[identifierIndex].compareTo(Wall.getWallName()) == 0){
                    Wall wall = new Wall(objectPosition, Wall.getWallWidth(), Wall.getWallHeight(),
                                        Wall.getWallBaseDamage(), Wall.getWallName());
                    gameEntities.add(wall);
                }

                else if (currentLine[identifierIndex].compareTo((Sinkhole.getSinkholeName())) == 0){
                    Sinkhole sinkhole = new Sinkhole(objectPosition, Sinkhole.getSinkholeWidth(),
                                                Sinkhole.getSinkholeHeight(), Sinkhole.getSinkholeDamage(),
                                                Sinkhole.getSinkholeName());
                    gameEntities.add(sinkhole);
                }


                    // Determine to boundaries of the game from the CSV information
                else if (currentLine[identifierIndex].compareTo(TOP_LEFT_INDICATOR) == 0){
                        topLeft = new Point(Double.parseDouble(currentLine[xCoordIndex]),
                                Double.parseDouble(currentLine[yCoordIndex]));
                } else if (currentLine[identifierIndex].compareTo(BOTTOM_RIGHT_INDICATOR) == 0){
                        bottomRight = new Point(Double.parseDouble(currentLine[xCoordIndex]),
                                Double.parseDouble(currentLine[yCoordIndex]));
                }

                // Create the rectangle that the game is played inside
                if (topLeft != null && bottomRight != null){
                    levelBounds = new Rectangle(topLeft, bottomRight.x - topLeft.x,
                            bottomRight.y - topLeft.x);
                }

            }
        // Catch an error in the CSV file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void drawStartScreen();
    public abstract void drawBackground();


}
