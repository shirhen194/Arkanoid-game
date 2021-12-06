package listeners;

import gameelements.Ball;
import gameelements.Block;
import gameelements.Counter;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a listeners.ScoreTrackingListener object with a score sprites.Counter,
 * and tracks the score.
 * <p>
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * This listeners.ScoreTrackingListener constructor. adds the given score Count,
     * as the score property.
     *
     * @param scoreCounter the counter of score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method increase the score on a hit event.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //increase the score count
        this.currentScore.increase(5);
    }
}