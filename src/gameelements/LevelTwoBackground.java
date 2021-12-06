package gameelements;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Point;


/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a gameelements.LevelTwoBackground object,
 * that gives the information about the second level.
 * <p>
 */
public class LevelTwoBackground implements Sprite {
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final Color skyColor = new Color(251, 196, 171);
    private final Color waterColor = new Color(46, 196, 182);
    private final Color sunColor = new Color(255, 186, 8);
    private final Point sunCenter = new Point(gameWidth / 2, gameHeight / 2);
    private final int sunSize = 130;


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
        d.setColor(this.skyColor);
        d.fillRectangle(0, 20, this.gameWidth, this.gameHeight / 2);

        //sunshine
        d.setColor(new Color(255, 159, 28));
        //left
        int xEnd = 0;
        int yEnd = this.gameHeight / 2;
        while (yEnd >= 20) {
            d.drawLine((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), xEnd, yEnd);
            yEnd = yEnd - 30;
        }

        //mid
        xEnd = 0;
        yEnd = 25;
        while (xEnd < this.gameWidth - 5) {
            xEnd = xEnd + 30;
            d.drawLine((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), xEnd, yEnd);
        }

        //right
        xEnd = this.gameWidth - 5;
        yEnd = 25;
        while (yEnd <= this.gameHeight / 2) {
            yEnd = yEnd + 30;
            d.drawLine((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), xEnd, yEnd);
        }

        //suns
        d.setColor(this.sunColor);
        d.fillCircle((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), this.sunSize);

        d.setColor(new Color(255, 195, 0));
        d.fillCircle((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), this.sunSize - 20);

        d.setColor(new Color(255, 208, 0));
        d.fillCircle((int) this.sunCenter.getX(), (int) this.sunCenter.getY(), this.sunSize - 40);

        //sea
        d.setColor(new Color(78, 168, 222));
        d.fillRectangle(0, this.gameHeight / 2, this.gameWidth, this.gameHeight / 2);

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
