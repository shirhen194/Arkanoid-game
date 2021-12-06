package animation;

import biuoop.DrawSurface;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This interface an Animation.
 * Animation is in charge of changing the UI of the game,
 * by the current properties and determine when it should stop.
 */
public interface Animation {
    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method checks and returns id the game should stop.
     *
     * @return boolean should stop game
     */
    boolean shouldStop();
}