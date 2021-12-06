package gameelements;

import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Point;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a gameelements.LevelOneBackground object,
 * that gives the information about the first level.
 * <p>
 */
public class LevelOneBackground implements Sprite {
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final Color backColor = Color.black;
    private final Color targetColor = Color.GREEN;
    private final Point tCenter = new Point(gameWidth / 2, gameHeight / 3);


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
        //target
        d.setColor(targetColor);
        d.drawCircle((int) this.tCenter.getX(), (int) this.tCenter.getY(), 80);
        d.drawCircle((int) this.tCenter.getX(), (int) this.tCenter.getY(), 120);
        d.drawCircle((int) this.tCenter.getX(), (int) this.tCenter.getY(), 160);
        //lines
        //horizontal
        d.drawLine((int) this.tCenter.getX() - 180, (int) this.tCenter.getY(),
                (int) this.tCenter.getX() + 180, (int) this.tCenter.getY());
        //vertical
        d.drawLine((int) this.tCenter.getX(), (int) this.tCenter.getY() - 180,
                (int) this.tCenter.getX(), (int) this.tCenter.getY() + 180);
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
