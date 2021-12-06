package gameelements;
import geometry.Point;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a gameelements.CollisionInfo object with it's collision point, and collision object.
 * <p>
 */
public class CollisionInfo {
    //the sprites.CollisionInfo properties
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * The sprites.CollisionInfo constructor
     * <p>
     * This method adds all the given values to the sprites.CollisionInfo properties.
     * <p>
     *
     * @param collisionPoint  the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * This method return the point at which the collision occurs.
     * <p>
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * This method return the collidable object involved in the collision.
     * <p>
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}