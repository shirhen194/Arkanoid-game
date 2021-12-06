package gameelements;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */


public class Ball implements Sprite {
    //values
    private Point point;
    private int size;
    private int gameWidth = 0;
    private int gameHeight = 0;
    private int frameSize = 5;
    private Velocity velocity;
    private Color color;
    private GameEnvironment gameEnvironment;
    private final int topScreenSize = 20;

    /**
     * The sprites.Ball constructor
     * <p>
     * This method adds all the given values to the ball variables.
     * <p>
     *
     * @param color  the color of the sprites.Ball.
     * @param center the center point of the ball.
     * @param r      the radius of the ball circle.
     */
    public Ball(Point center, int r, Color color) {
        this.point = center;
        this.size = r;
        this.color = color;
    }

    // constructor

    /**
     * The sprites.Ball constructor
     * <p>
     * This method adds all the given values to the ball variables.
     * <p>
     *
     * @param color the color of the sprites.Ball.
     * @param x     the center x point of the ball.
     * @param y     the center y point of the ball.
     * @param r     the radius of the ball circle.
     */
    public Ball(double x, double y, int r, Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
    }

    /**
     * The sprites.Ball constructor
     * <p>
     * This method adds all the given values to the ball properties.
     * <p>
     *
     * @param color           the color of the sprites.Ball.
     * @param center          the center point of the ball.
     * @param r               the radius of the ball circle.
     * @param gameEnvironment the gameEnvironment of the ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.point = center;
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        //add a default velocity
        this.velocity = new Velocity(5, -5);
    }

    /**
     * The gameelements.Ball constructor
     * <p>
     * This method adds all the given values to the ball properties.
     * <p>
     *
     * @param color           the color of the sprites.Ball.
     * @param center          the center point of the ball.
     * @param r               the radius of the ball circle.
     * @param gameEnvironment the gameEnvironment of the ball.
     * @param gameHeight      the game_height of the game.
     * @param gameWidth       the gameEnvironment of the game.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment,
                int gameWidth, int gameHeight) {
        this.point = center;
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        //add a default velocity
        this.velocity = new Velocity(4, -4);
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
    }

    /**
     * This method sets the value of the object velocity.
     * <p>
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method sets the value of the object velocity.
     * <p>
     *
     * @param dx dx of the velocity to set.
     * @param dy dy of the velocity to set.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * <p>
     * This method "moves" the ball by changing its center to a value by the velocity,
     * while using the limit values.
     * </p>
     *
     * @param xLimitEnd   the x right limit of the ball.
     * @param yLimitEnd   the y right limit of the ball.
     * @param xLimitStart the x left limit of the ball.
     * @param yLimitStart the y left limit of the ball.
     */
    public void moveOneStep(int xLimitEnd, int yLimitEnd, int xLimitStart, int yLimitStart) {
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        //if reached x right limit
        if ((this.getX() + this.getSize() + dx) >= xLimitEnd) {
            dx = -dx;
        }
        //if reached y right limit
        if ((this.getY() + this.getSize() + dy) >= yLimitEnd) {
            dy = -dy;
        }
        //if reached beginning in x
        if ((this.getX() - this.getSize() + dx) <= xLimitStart) {
            dx = -dx;
        }
        //if reached beginning in y
        if ((this.getY() - this.getSize() + dy) <= yLimitStart) {
            dy = -dy;
        }
        //set the velocity by the dy and dx and move the ball by it
        this.setVelocity(dx, dy);
        this.point = this.getVelocity().applyToPoint(this.point);
    }


    /**
     * <p>
     * This method "moves" the ball by changing its center to a value by the velocity,
     * while using the limit values, in the game environment.
     * </p>
     */
    public void moveOneStep() {
        if (this.gameEnvironment == null) {
            this.point = this.getVelocity().applyToPoint(this.point);
        }
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();

        //if we got game width and height
        if (this.gameHeight > 0 && this.gameWidth > 0) {
            //if reached x right limit
            if ((this.getX() + this.getSize() + dx) >= this.gameWidth) {
                dx = -dx;
                //if reached y right limit
                if ((this.getY() + this.getSize() + dy) >= this.gameHeight) {
                    dy = -dy;
                }
            }
            //if reached beginning in x
            if ((this.getX() - this.getSize() + dx) <= frameSize) {
                dx = -dx;
                //if reached y right limit
                if ((this.getY() + this.getSize() + dy) >= this.gameHeight) {
                    dy = -dy;
                }
            }
            //if reached beginning in y
            if ((this.getY() - this.getSize() + dy) <= frameSize + topScreenSize) {
                dy = -dy;
            }
            this.setVelocity(dx, dy);
        }


        //create the trajectory line representing the ball's course.
        double startX = this.point.getX();
        double startY = this.point.getY();
        double endX = this.point.getX() + dx;
        double endY = this.point.getY() + dy;
        if (dx < 0) {
            endX -= this.size;
            startX += this.size;
        } else {
            endX += this.size;
            startX -= this.size;
        }
        if (dy < 0) {
            endY -= this.size;
            startY += this.size;
        } else {
            endY += this.size;
            startY -= this.size;
        }
        Point courseEnd = new Point(endX, endY);
        Point courseStart = new Point(startX, startY);
        Line trajectory = new Line(courseStart, courseEnd);

        /**
         * Check (using the game environment) if moving on this trajectory will hit anything.
         */
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.point = this.getVelocity().applyToPoint(this.point);
            return;
        }
        Collidable collisionObj = collisionInfo.collisionObject();
        Velocity newVelocity = collisionObj.hit(this, collisionInfo.collisionPoint(), this.getVelocity());
        this.setVelocity(newVelocity);
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * This method gets the value of the object velocity.
     * <p>
     *
     * @return v = the velocity object of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method gets the value of the x center point of the ball.
     * <p>
     *
     * @return x = the x center point of the ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * This method gets the value of the y center point of the ball.
     * <p>
     *
     * @return y = the y center point of the ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * This method gets the value of the the size(radius) of the ball.
     * <p>
     *
     * @return size = the size(radius) of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * This method gets the value of the object color.
     * <p>
     *
     * @return color = the color object of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * <p>
     * This method draws the ball on the given DrawSurface.
     * </p>
     *
     * @param surface = the DrawSurface object.
     */
    public void drawOn(DrawSurface surface) {
        if (this.color != null) {
            surface.setColor(this.color);
        } else {
            surface.setColor(Color.white);
        }        //draws on surface
        surface.fillCircle((int) this.point.getX(), (int) this.point.getY(), this.size);
        //border
        surface.setColor(Color.black);
        surface.drawCircle((int) this.point.getX(), (int) this.point.getY(), this.size);
    }

    /**
     * <p>
     * This method sets a new gameEnvironment for the ball.
     * </p>
     *
     * @param gameEnv = the gameEnvironment object to set.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnvironment = gameEnv;
    }

    /**
     * <p>
     * This method will do animation work for the ball after time, meaning
     * calling moveOneStep.
     * </p>
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * <p>
     * This method adds the ball the the game as a sprite.
     * </p>
     *
     * @param game the ball game object
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * <p>
     * This method removes the ball the the game as a sprite.
     * </p>
     *
     * @param g the ball game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}