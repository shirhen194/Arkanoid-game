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
 * This class generates a sprites.Ball object.
 * listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * <p>
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * the listeners.BlockRemover constructor.
     * @param game the game object
     * @param removedBlocks the counter of removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * This method removes the sprites.Block from the game, and the listener from the sprites.Block.
     * @param beingHit the sprites.Block that was hit by the ball
     * @param hitter the sprites.Ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //decrease the sprites.Block count
        this.remainingBlocks.decrease(1);
        //remove listener from the block
        beingHit.removeHitListener(this);

        //remove block from the game
        beingHit.removeFromGame(this.game);
    }
}