package level;

import gameelements.Block;
import gameelements.LevelOneBackground;
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
 * level one class level information object.
 */
public class LevelOne implements LevelInformation {
    private int numOfBalls = 1;
    private Velocity lev1BallVelocity = new Velocity(0, -7);
    private List<Velocity> bellsVelocities = new ArrayList<>();
    private int paddleSpeed = 70;
    private int paddleWidth = 100;
    private String levelName = "Direct Hit";
    private Sprite background = new LevelOneBackground();
    private List<Block> blockList = new ArrayList<>();
    private int numberOfBlocksToRemove = 1;
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final double blockSize = 40;
    private final java.awt.Point tCenter = new java.awt.Point(gameWidth / 2, gameHeight / 3);

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
        this.bellsVelocities.add(lev1BallVelocity);
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
        double upperLeftX = (int) (this.tCenter.getX() - this.blockSize / 2);
        double upperLeftY = (int) (this.tCenter.getY() - this.blockSize / 2);

        //create the blocks in the row with their color
        Point upperLeft = new Point(upperLeftX, upperLeftY);
        Rectangle rect = new Rectangle(upperLeft, this.blockSize, this.blockSize, Color.RED);
        Block block = new Block(rect);
        this.blockList.add(block);
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
