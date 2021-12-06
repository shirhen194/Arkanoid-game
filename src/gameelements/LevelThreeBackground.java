package gameelements;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a gameelements.LevelThreeBackground object,
 * that gives the information about the third level.
 * <p>
 */
public class LevelThreeBackground implements Sprite {
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final Color backColor = new Color(202, 255, 191);

    @Override
    /**
     * <p>
     * This method draws the level on the given DrawSurface.
     * </p>
     *
     * @param surface = the DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        //background
        d.setColor(this.backColor);
        d.fillRectangle(0, 20, this.gameWidth, this.gameHeight);
    }

    @Override
    /**
     * <p>
     * This method will do animation work in the future.
     * </p>
     */
    public void timePassed() {
    }
}
