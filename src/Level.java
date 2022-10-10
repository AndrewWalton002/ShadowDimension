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
    private int frameCounter = 0;
    private final int WIN_SCREEN_TIME = 3;
    private int WIN_SCREEN_FRAMES = WIN_SCREEN_TIME * ShadowDimension.getRefreshRate();
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getWIN_SCREEN_FRAMES() {
        return WIN_SCREEN_FRAMES;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
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

    public Player getPlayer() {
        return player;
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
        //Skip to level one
        if (input.wasPressed(Keys.W)){
            ShadowDimension.getInstance().levelUp();
            return;
        }

        switch (gameState){
            case START_SCREEN:
                drawStartScreen();
                break;
            case LEVEL_RUNNING:
                drawBackground();
                gameEntities.forEach((gameEntity -> gameEntity.updateGameEntity(input)));
                player.updateGameEntity(input);
                break;
            case GAME_LOST:
                drawLossScreen();
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
                if (player.isDead()){
                    gameState = GameState.GAME_LOST;
                } else if (hasBeatLevel()){
                    gameState = GameState.LEVEL_WON;
                }




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

                else if (currentLine[identifierIndex].compareTo(Tree.getTreeName()) == 0){
                    Tree tree = new Tree(objectPosition, Tree.getTreeWidth(), Tree.getTreeHeight(),
                                        Tree.getTreeDamage(), Tree.getTreeName());
                    gameEntities.add(tree);
                }

                else if (currentLine[identifierIndex].compareTo(Demon.getDemonName()) == 0){
                    // Need to adjust for aggressive demon randomly
                    Demon demon = new Demon(objectPosition, Demon.getDemonWidth(), Demon.getDemonHeight(),
                                        Demon.getDemonDamage(), Demon.getDemonName(), Demon.getDemonMaxHealth());
                    gameEntities.add(demon);
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

    public void drawLossScreen(){

        Font defaultFont = ShadowDimension.getInstance().getDefaultFont();
        defaultFont.drawString(LOSS_MESSAGE, ShadowDimension.getWindowWidth()/2 -
                            defaultFont.getWidth(LOSS_MESSAGE)/2, ShadowDimension.getWindowHeight()/2 -
                            ShadowDimension.getInstance().getDEFAULT_FONT_SIZE()/2);

    }

    public abstract void drawStartScreen();
    public abstract void drawBackground();
    public abstract boolean hasBeatLevel();
    public abstract void drawLevelWinScreen();


}
