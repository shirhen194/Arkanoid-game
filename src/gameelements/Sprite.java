package gameelements;

import biuoop.DrawSurface;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a sprites.Sprite interface.
 * <p>
 */
public interface Sprite {

    /**
     * This method draw the sprite to the screen.
     *
     * @param d the draw surface of the game
     */
    void drawOn(DrawSurface d);

    /**
     * This method notify the sprite that time has passed.
     */
    void timePassed();
}