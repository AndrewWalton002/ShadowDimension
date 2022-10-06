import bagel.Image;
import bagel.util.Point;

public class Sinkhole extends StationaryEntity{
    private static final String SINKHOLE_PATH = "res/sinkhole.png";
    private static final Image SINKHOLE_IM = new Image(SINKHOLE_PATH);
    private static final int SINKHOLE_WIDTH = 62;
    private static final int SINKHOLE_HEIGHT = 37;
    private static final int SINKHOLE_DAMAGE = 30;
    private static final String SINKHOLE_NAME = "Sinkhole";



    public static int getSinkholeWidth(){
        return SINKHOLE_WIDTH;
    }
    public static int getSinkholeHeight(){
        return SINKHOLE_HEIGHT;
    }
    public static int getSinkholeDamage(){
        return SINKHOLE_DAMAGE;
    }
    public static String getSinkholeName(){
        return SINKHOLE_NAME;
    }
    public Sinkhole(Point position, int width, int height, double BASE_DAMAGE, String name) {
        super(position, width, height, BASE_DAMAGE, name);
    }

    public void updateSinkhole(){
        drawGameEntity(SINKHOLE_IM);
    }
}
