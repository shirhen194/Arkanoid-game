package gameelements;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a gameelements.Block object with a upperLeft point,width color and height.
 * <p>
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * The sprites.Block constructor
     * <p>
     * This method adds all the given values to the sprites.Block variables.
     * <p>
     *
     * @param upperLeft upper left point of the block
     * @param width     the block width
     * @param height    the block height
     * @param color     the block given color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * The sprites.Block constructor
     * <p>
     * This method adds all the given values to the sprites.Block variables.
     * <p>
     *
     * @param rect the rectangle that defines the block
     */
    public Block(Rectangle rect) {
        this.upperLeft = rect.getUpperLeft();
        this.width = rect.getWidth();
        this.height = rect.getHeight();
        if (rect.getColor() == null) {
            this.color = Color.BLACK;
        } else {
            this.color = rect.getColor();
        }
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
     * This method notifies a hit in the block.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * <p>
     * This method notifies the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * </p>
     *
     * @param hitter          the hitting ball
     * @param collisionPoint  the point of the collusion
     * @param currentVelocity the current velocity of the object hitting
     * @return the new velocity expected after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //get the velocity properties
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //get the bounding block lines
        Line left = this.getCollisionRectangle().getLeft();
        Line right = this.getCollisionRectangle().getRight();
        Line top = this.getCollisionRectangle().getTop();
        Line bottom = this.getCollisionRectangle().getBottom();
        //check if the object hit vertically
        if (top.isPointInLine(collisionPoint) || bottom.isPointInLine(collisionPoint)) {
            dy = -dy;
        }
        //check if the object hit horizontally
        if (left.isPointInLine(collisionPoint) || right.isPointInLine(collisionPoint)) {
            dx = -dx;
        }
        //create the new velocity based on the change in dx and dy
        Velocity newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    /**
     * <p>
     * This method draws the block on the given DrawSurface.
     * </p>
     *
     * @param surface = the DrawSurface object.
     */
    public void drawOn(DrawSurface surface) {
        if (this.color != null) {
            surface.setColor(this.color);
        } else {
            surface.setColor(Color.black);
        }
        surface.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
    }

    /**
     * <p>
     * This method will do animation work in the future.
     * </p>
     */
    public void timePassed() {
        //will fill in future
    }

    /**
     * <p>
     * This method adds the block to the game as a sprite and a collidable.
     * </p>
     *
     * @param game the block containing game object
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * <p>
     * This method removes the block from the game as a sprite and a collidable.
     * </p>
     *
     * @param game the block containing game object
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    /**
     * This method adds the given listener to the hit listeners list.
     * @param hl a hit listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes the given listener from the hit listeners list.
     *
     * @param hl a hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
