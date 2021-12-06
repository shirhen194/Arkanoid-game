package level;
import gameelements.Block;
import gameelements.Sprite;
import gameelements.Velocity;

import java.util.List;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * level information interface for describing each level.
 */
public interface LevelInformation {
    /**
     * This method returns the number of balls.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * This method returns the initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * This method returns the width of the paddle.
     *
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * This method generates the level name.
     * The level name will be displayed at the top of the screen.
     *
     * @return The level name
     */
    String levelName();

    /**
     * This method returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * This method returns the Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * This method returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return he number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
