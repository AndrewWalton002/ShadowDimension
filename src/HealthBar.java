import bagel.DrawOptions;
import bagel.Font;
import bagel.util.Colour;
import bagel.util.Point;

public class HealthBar {
    private final double RED_RED_VALUE = 1;
    private final double RED_GREEN_VALUE = 0;
    private final double RED_BLUE_VALUE = 0;
    private final double ORANGE_RED_VALUE = 0.9;
    private final double ORANGE_GREEN_VALUE = 0.6;
    private final double ORANGE_BLUE_VALUE = 0;
    private final double GREEN_RED_VALUE = 0;
    private final double GREEN_GREEN_VALUE = 0.8;
    private final double GREEN_BLUE_VALUE = 0.2;
    private final Colour RED = new Colour(RED_RED_VALUE, RED_GREEN_VALUE, RED_BLUE_VALUE);
    private final Colour ORANGE = new Colour(ORANGE_RED_VALUE, ORANGE_GREEN_VALUE, ORANGE_BLUE_VALUE);
    private final Colour GREEN = new Colour(GREEN_RED_VALUE, GREEN_GREEN_VALUE, GREEN_BLUE_VALUE);
    private final int ORANGE_THRESHOLD = 65;
    private final int RED_THRESHOLD = 35;
    private static final int PERCENTAGE_MULTIPLIER = 100;
    private final int FONT_SIZE;
    private final Font HEALTH_BAR_FONT;
    private DrawOptions colourOptions = new DrawOptions();



    public HealthBar(int FONT_SIZE) {
        this.FONT_SIZE = FONT_SIZE;
        this.HEALTH_BAR_FONT = new Font(ShadowDimension.getInstance().getFontPath(), FONT_SIZE);
    }

    public void updateHealthBar(Point position, double currentHealth, double maxHealth){
        int healthPercentage = (int)((currentHealth / maxHealth) * PERCENTAGE_MULTIPLIER);

        Colour healthBarColour = healthBarColour(healthPercentage);

        drawHealthBar(position, healthPercentage, healthBarColour);
    }

    public Colour healthBarColour(int healthPercentage){
        if (healthPercentage <= RED_THRESHOLD) {
            return RED;
        } else if (healthPercentage <= ORANGE_THRESHOLD){
            return ORANGE;
        } else {
            return GREEN;
        }
    }

    public void drawHealthBar(Point position, int healthPercentage, Colour colour){
        HEALTH_BAR_FONT.drawString(healthPercentage + "%", position.x, position.y,
                                    colourOptions.setBlendColour(colour));
    }
}
