import bagel.*;
import bagel.util.*;

public abstract class GameEntity extends Rectangle implements Drawable {
    private Point position;
    private int width;
    private int height;

    private final double BASE_DAMAGE;
    private final String NAME;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getBASE_DAMAGE() {
        return BASE_DAMAGE;
    }

    public String getNAME() {
        return NAME;
    }


    public GameEntity(Point position, int width, int height, double BASE_DAMAGE, String NAME){
        super(position.x, position.y, width, height);
        this.position = position;
        this.width = width;
        this.height = height;
        this.BASE_DAMAGE = BASE_DAMAGE;
        this.NAME = NAME;
    }

    public void drawGameEntity(Image image){
        image.drawFromTopLeft(position.x, position.y);
    }

    public abstract void updateGameEntity(Input input);
}
