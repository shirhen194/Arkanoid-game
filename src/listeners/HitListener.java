package listeners;

import gameelements.Ball;
import gameelements.Block;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This interface generates a HitListener.
 * HitListener is in charge of listening to the a block hits by the ball,
 * so that an action will be made.
 * <p>
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     * @param beingHit the sprites.Block that was hit by the ball
     * @param hitter the sprites.Ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}