import bagel.*;
import bagel.util.*;

public abstract class GameEntity extends Rectangle implements Comparable<GameEntity>{
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

    @Override
    public int compareTo(GameEntity o){
        if (this.NAME.compareTo(o.NAME) == 0 && this.position.x == o.position.x && this.position.y == o.position.y){
            return 0;
        } else if (this.position.x != o.position.x){
            return (int)(this.position.x - o.position.x);
        } else if (this.position.y != o.position.y) {
            return (int) (this.position.y - o.position.y);
        } else {
            return this.NAME.compareTo(o.NAME);
        }
    }

    public abstract void updateGameEntity(Input input);
}
