package gameelements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;


/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a sprites.Paddle object with it's properties.
 * <p>
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private double width;
    private double height;
    private Point upperLeft;
    private Color color;
    private double rightLimit;
    private double leftLimit = 0;
    private double xVelocity;

    /**
     * The sprites.Paddle constructor
     * <p>
     * This method adds the given values to the sprites.Paddle variables.
     * <p>
     *
     * @param keyboard   the keyboard of the starting point of the sprites.Paddle
     * @param upperLeft  the starting point of the sprites.Paddle
     * @param width      the width of the sprites.Paddle
     * @param height     the height of the sprites.Paddle
     * @param rightLimit the rightLimit of the sprites.Paddle
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, double width, double height, double rightLimit) {
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.rightLimit = rightLimit;
        this.xVelocity = 15;
    }

    /**
     * The sprites.Paddle constructor
     * <p>
     * This method adds the given values to the sprites.Paddle variables.
     * <p>
     *
     * @param keyboard   the keyboard of the starting point of the sprites.Paddle
     * @param upperLeft  the starting point of the sprites.Paddle
     * @param width      the width of the sprites.Paddle
     * @param height     the height of the sprites.Paddle
     * @param color      the height of the sprites.Paddle
     * @param rightLimit the rightLimit of the sprites.Paddle
     * @param leftLimit  the left limit of the sprites.Paddle
     */
    public Paddle(KeyboardSensor keyboard, Point upperLeft, double width, double height, Color color,
                  double rightLimit, double leftLimit) {
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = color;
        this.rightLimit = rightLimit;
        this.leftLimit = leftLimit;
        this.xVelocity = 15;
    }

    /**
     * This method moves the paddle upper left point to the left by it's velocity.
     */
    public void moveLeft() {
        double dx = this.xVelocity;
        double nextX = this.upperLeft.getX() - dx;
        //if didnt reach left, move, if it did, dont move.
        if (nextX > this.leftLimit) {
            this.upperLeft = new Point(nextX, this.upperLeft.getY());
        }
    }

    /**
     * This method moves the paddle upper left point to the right by it's velocity.
     */
    public void moveRight() {
        double dx = this.xVelocity;
        double nextX = dx + this.upperLeft.getX() + this.width;
        //if didnt reach right, move, if it did, dont move.
        if (nextX < this.rightLimit) {
            this.upperLeft = new Point(dx + this.upperLeft.getX(), this.upperLeft.getY());
        }
    }

    /**
     * This method checks if a key was pressed to move the paddle to the right or left.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * <p>
     * This method draws the paddle on the given DrawSurface.
     * </p>
     *
     * @param d = the DrawSurface object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        if (this.color != null) {
            d.setColor(this.color);
        }
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
    }

    /**
     * This method returns the "collision shape" of the object.
     * <p>
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        Rectangle collusionRect = new Rectangle(this.upperLeft, this.width, this.height);
        return collusionRect;
    }

    /**
     * This method generates a new velocity fir a hitting object.
     *
     * @param hitter          the hitting ball
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity of the hitting object
     * @return the new velocity created
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //get the velocity properties
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //get the bounding block top line
        Line top = this.getCollisionRectangle().getTop();
        Line left = this.getCollisionRectangle().getLeft();
        Line right = this.getCollisionRectangle().getRight();
        double rectWidth = this.getCollisionRectangle().getWidth();
        Line[] padRegions = new Line[5];
        double topX = top.start().getX();
        double topY = top.start().getY();
        double topEndX = top.end().getX();
        for (int i = 0; i < 5; i++) {
            Point start = new Point(topX + (rectWidth / 5) * i, topY);
            Point end = new Point(topEndX - (rectWidth / 5) * (5 - (i + 1)),
                    topY);
            padRegions[i] = new Line(start, end);
        }
        Line region1 = padRegions[0];
        Line region2 = padRegions[1];
        Line region3 = padRegions[2];
        Line region4 = padRegions[3];
        Line region5 = padRegions[4];

        /**
         * check which region was hit, and retrurn a modified velocity accordingly.
         * leftmost - region 1 - bounce in 300 degrees
         * lef - region 2 - bounce in 330 degrees
         * middle - region 3 - change only vertical direction
         * right - region 4 - bounce in 30 degrees
         * rightmost - region 5 - bounce in 60 degrees
         */
        double vectorSpeed = Math.sqrt(dx * dx + dy * dy);

        //if the ball hit the sides of the paddle
        if (left.isPointInLine(collisionPoint) || right.isPointInLine(collisionPoint)) {
            dx = -dx;
        }
        //y is the opposite direction so 150 will be 300
        if (region1.isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(150, vectorSpeed);
        }
        //y is the opposite direction so 120 will be 330
        if (region2.isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(120, vectorSpeed);
        }
        //if it hit the middle region, it should return in opposite direction
        if (region3.isPointInLine(collisionPoint)) {
            dy = -dy;
        }
        //y is the opposite direction so 30 will be 60
        if (region4.isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, vectorSpeed);
        }
        //y is the opposite direction so 60 will be 30
        if (region5.isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, vectorSpeed);
        }
        return new Velocity(dx, dy);
    }

    // Add this paddle to the game.

    /**
     * <p>
     * This method adds this paddle to the game as a collidable.
     * </p>
     *
     * @param g the block containing game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}