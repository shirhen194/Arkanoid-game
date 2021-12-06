package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import level.LevelInformation;
import gameelements.Ball;
import gameelements.Block;
import gameelements.Collidable;
import gameelements.Counter;
import gameelements.GameEnvironment;
import gameelements.Paddle;
import gameelements.ScoreIndicator;
import gameelements.Sprite;
import gameelements.SpriteCollection;
import geometry.Point;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;

import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a game.Game object with it's sprites,sleeper and game env object.
 * <p>
 */
public class GameLevel implements Animation {
    private final int gameHeight = 600;
    private final int gameWidth = 800;
    private AnimationRunner runner;
    private boolean running;
    private GUI gui;
    private Sleeper sleeper = new Sleeper();
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score = new Counter();
    private final int frameSize = 5;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Color paddleColor = new Color(253, 201, 24);
    private final int topScreenSize = 20;
    private int paddleHeight = 10;
    private int paddleWidth;
    private int paddleUpperX;
    private int paddleUpperY = gameHeight - paddleHeight;
    private int ballRad = 7;
    private AnimationRunner animationRunner;

    /**
     * The game.Game constructor.
     * <p>
     * This method defines the game sprites,sleeper and game env object
     * <p>
     *
     * @param levelInfo the level information object for current running level
     */
    public GameLevel(LevelInformation levelInfo) {
        this.levelInformation = levelInfo;
        this.keyboard = this.gui.getKeyboardSensor();
        this.paddleWidth = this.levelInformation.paddleWidth();
        this.paddleUpperX = (this.gameWidth / 2) - this.paddleWidth / 2;
    }

    /**
     * The game.Game constructor.
     * <p>
     * This method defines the game sprites,sleeper and game env object
     * <p>
     *
     * @param levelInfo the level information object for current running level
     * @param k         KeyboardSensor
     * @param r         AnimationRunner
     * @param sC        score
     * @param gui       the gui object
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor k, AnimationRunner r,
                     Counter sC, GUI gui) {
        this.levelInformation = levelInfo;
        this.keyboard = k;
        this.paddleWidth = this.levelInformation.paddleWidth();
        this.paddleUpperX = (this.gameWidth / 2) - this.paddleWidth / 2;
        this.blockCounter.increase(levelInfo.numberOfBlocksToRemove());
        this.animationRunner = r;
        this.score = sC;
        this.gui = gui;
    }

    /**
     * This method adds the given collidable to the game environment.
     *
     * @param c the given collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds the given sprite to the sprites.
     *
     * @param s the given sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {

        this.sprites.addSprite(this.levelInformation.getBackground());

        //generate paddle with it's properties
        Point paddleUpperLeft = new Point(paddleUpperX, paddleUpperY);
        Paddle paddle = new Paddle(this.keyboard, paddleUpperLeft, this.paddleWidth,
                paddleHeight, this.paddleColor, gameWidth - frameSize, 0 + frameSize);
        paddle.addToGame(this);
        //call the method to create the balls and add them to game.
        createGameBalls();
        //call the method that generates the blocks in pattern and adds them game.
        this.drawBlocksPattern();
        //call the method to create the bounding blocks and add them to game.
        createBoundingBlocks();
        ScoreIndicator scoreSprite = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreSprite);
    }

    /**
     * This method creates the game balls and adds them to game.
     */
    private void createGameBalls() {
        int numOfBalls = levelInformation.numberOfBalls();
        int ballStarty = gameHeight - 10 - frameSize - ballRad;
        /**
         * run a loop to generate the balls properties and add them to the game
         */
        for (int i = 0; i < numOfBalls; i++) {
            int ballStartX;
            int padWidthForBalls = this.paddleWidth - this.ballRad;
            int padxForBalls = this.paddleUpperX + this.ballRad;

            //if only one ball start at center
            if (numOfBalls == 1) {
                ballStartX = this.paddleUpperX + this.paddleWidth / 2;
            } else {
                if (this.paddleWidth > 300) {
                    padxForBalls = (this.gameWidth / 2) - 300 / 2 + this.ballRad;
                    ballStartX = padxForBalls + (300 / (numOfBalls - 1)) * i;
                } else {
                    ballStartX = padxForBalls + (padWidthForBalls / (numOfBalls - 1)) * i;
                }
            }
            Point ballStart = new Point(ballStartX, ballStarty);
            Ball ball = new Ball(ballStart, this.ballRad, Color.white, this.environment,
                    gameWidth - frameSize, gameHeight + frameSize);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.ballCounter.increase(1);
            ball.addToGame(this);
        }

    }

    /**
     * This method creates the game bounding blocks and adds them to game.
     */
    private void createBoundingBlocks() {
        //create ball remover listener for the bottom block
        BallRemover ballRemoverListener = new BallRemover(this, this.ballCounter);
        Color gold = new Color(232, 176, 23);
        //add the frame blocks
        Block top = new Block(new Point(0, this.topScreenSize), this.gameWidth, this.frameSize, gold);
        top.addToGame(this);
        Block left = new Block(new Point(0, this.topScreenSize), this.frameSize, this.gameHeight, gold);
        left.addToGame(this);
        Block bottom = new Block(new Point(0, this.gameHeight + 14),
                this.gameWidth, 5, gold);
        bottom.addHitListener(ballRemoverListener);
        bottom.addToGame(this);
        Block right = new Block(new Point(this.gameWidth - this.frameSize, this.topScreenSize),
                this.frameSize, this.gameHeight, gold);
        right.addToGame(this);
    }

    /**
     * This method draws the blocks in pattern and adds them to the game.
     */
    public void drawBlocksPattern() {
        //create block remover listener
        BlockRemover blockRemoveListener = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        /**
         * loop and create the Blocks with calculated properties.
         */
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            //get current block
            Block block = this.levelInformation.blocks().get(i);
            //add block remover and score listener
            block.addHitListener(blockRemoveListener);
            block.addHitListener(scoreListener);
            //add the block to the game
            block.addToGame(this);
        }
    }

    /**
     * This method runs the game, start the animation loop.
     */
    public void run() {
        //define the game timing animation properies
        int framesPerSecond = 60;

        //this.createBallsOnTopOfPaddle();
        this.running = true;

        Animation countDownAnimation = new CountdownAnimation(2, 3, this.sprites);
        this.runner = new AnimationRunner(this.gui, framesPerSecond, this.sleeper);
        this.runner.setFramesPerSecond(3 / 2);

        this.runner.run(countDownAnimation); // countdown before turn starts.
        this.runner.setFramesPerSecond(60);

        this.runner.run(this);
    }

    /**
     * This method removes given collidable from the collidables.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method removes given sprite from the sprites.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method draws the upper screen with info.
     *
     * @param d The drawSurface object
     */
    public void drawInfo(DrawSurface d) {
        //draw upper screen info
        String levelName = "Level Name: " + this.levelInformation.levelName();
        d.setColor(Color.black);
        d.drawText(570, 17, levelName, 17);
    }

    @Override
    /**
     * This method runs the animation for one frame.
     *
     * @param d the DrawSurface object
     */
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        this.drawInfo(d);
        //pause screen
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            Animation pauseAnimation = new PauseScreen();
            String spaceKey = KeyboardSensor.SPACE_KEY;
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, spaceKey, pauseAnimation));

        }
//        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
////            this.running = false;
//            return;
//        }
    }

    @Override
    /**
     * this method checks and returns id the game should stop.
     *
     * @return boolean should stop game
     */
    public boolean shouldStop() {
        return this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0;
    }


    /**
     * A getter for the amount of blocks in game.
     *
     * @return the amount of blocks in game
     */
    public int getBlockCounter() {
        return this.blockCounter.getValue();
    }

    /**
     * A getter for the amount of balls in game.
     *
     * @return the amount of balls in game
     */
    public int getBallCounter() {
        return this.ballCounter.getValue();
    }
}