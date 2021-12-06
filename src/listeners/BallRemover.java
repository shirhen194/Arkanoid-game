package listeners;

import game.GameLevel;
import gameelements.Ball;
import gameelements.Block;
import gameelements.Counter;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a listeners.BallRemover object with a game.Game object and sprites.Counter object.
 * <p>
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * the listeners.BallRemover constructor,
     * that adds all properties to the listeners.BallRemover object.
     *
     * @param game         the game.Game object
     * @param removedBalls the counter of the removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Balls that hit the bottom block should be removed from the game.
     * This method removes the sprites.Ball from the game, and the listener from the sprites.Block
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //decrease the sprites.Ball count
        this.remainingBalls.decrease(1);
        //remove block from the game
        hitter.removeFromGame(this.game);
    }
}
