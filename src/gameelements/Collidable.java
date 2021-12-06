package gameelements;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This interface generates a gameelements.Collidable object.
 * <p>
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * This method return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * This method Notifies the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point of the colllision
     * @param currentVelocity the current velocity of the hitting object
     * @param hitter          the hitting ball
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}