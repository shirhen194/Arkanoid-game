package animation;

import biuoop.DrawSurface;
import gameelements.SpriteCollection;

import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * The CountdownAnimation constructor. properties:
     *
     * @param numOfSeconds number of seconds to run
     * @param countFrom    the number to count from
     * @param gameScreen   the game SpriteCollection
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    public void doOneFrame(DrawSurface d) {
        //draw all the game elements then the countDown
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(400, d.getHeight() / 2, String.valueOf(this.countFrom), 32);
        this.countFrom--;
    }

    /**
     * this method checks and returns id the game should stop.
     *
     * @return boolean should stop game
     */
    public boolean shouldStop() {
        return this.countFrom == 0;
    }
}