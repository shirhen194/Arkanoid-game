package level;

import gameelements.Block;
import gameelements.LevelTwoBackground;
import gameelements.Sprite;
import gameelements.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * level wo class level information object.
 */
public class LevelTwo implements LevelInformation {
    private int ballAngle = 30;
    private final int ballSpeed = 7;
    private int numOfBalls = 11;
    private List<Velocity> bellsVelocities = new ArrayList<>();
    private int paddleSpeed = 70;
    private int paddleWidth = 600;
    private String levelName = "Wide Easy";
    private Sprite background = new LevelTwoBackground();
    private List<Block> blockList = new ArrayList<>();
    private int numberOfBlocksToRemove = 15;
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final int blockSize = 20;
    private final int frameSize = 5;
    private final int blockWidth = ((this.gameWidth - this.frameSize * 2) / numberOfBlocksToRemove);
    private final Color[] colors = {new Color(16, 93, 167), new Color(16, 93, 167),
            new Color(140, 211, 242), new Color(140, 211, 242), new Color(34, 167, 83),
            new Color(34, 167, 83), new Color(165, 199, 63), new Color(165, 199, 63),
            new Color(165, 199, 63), new Color(253, 201, 24), new Color(253, 201, 24),
            new Color(239, 130, 71), new Color(239, 130, 71), new Color(229, 28, 41),
            new Color(229, 28, 4)};

    @Override
    /**
     * This method returns the number of balls.
     *
     * @return the number of balls
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    /**
     * This method returns the initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        Velocity vel = new Velocity(1, 1);

        for (int i = 1; i <= this.numOfBalls; i++) {
            vel = vel.fromAngleAndSpeed(this.ballAngle, this.ballSpeed);
            this.ballAngle = this.ballAngle + 15;
            this.bellsVelocities.add(vel);
        }
        return this.bellsVelocities;
    }

    @Override
    /**
     * This method returns the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    /**
     * This method returns the width of the paddle.
     *
     * @return the width of the paddle
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    /**
     * This method generates the level name.
     * The level name will be displayed at the top of the screen.
     *
     * @return The level name
     */
    public String levelName() {
        return this.levelName;
    }

    @Override
    /**
     * This method returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    /**
     * This method returns the Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        int upperLeftY = this.gameHeight / 2 - blockSize;

        /**
         * loop and create the Blocks with calculated properties.
         */
        for (int i = 0; i < this.numberOfBlocksToRemove; i++) {
            int currUpperLeftX = this.frameSize + blockWidth * i;
            //create the blocks in the row with their color
            Point currUpperLeft = new Point(currUpperLeftX, upperLeftY);
            Rectangle rect = new Rectangle(currUpperLeft, this.blockWidth, this.blockSize, colors[i]);
            Block block = new Block(rect);
            this.blockList.add(block);
        }
        return this.blockList;
    }

    @Override
    /**
     * This method returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return he number of blocks that should be removed
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
