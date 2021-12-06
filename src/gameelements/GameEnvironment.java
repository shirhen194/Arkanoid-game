package gameelements;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;

/**
 * @author Shir Hanono
 * @version 1.0 22/04/2021
 */

/**
 * This class generates a gameelements.GameEnvironment object with it's collidables.
 * <p>
 */
public class GameEnvironment {
    //the game environment collidable collection.
    private final ArrayList<Collidable> collidables;

    /**
     * The sprites.GameEnvironment constructor
     * <p>
     * This method creates the game environment collidables ArrayList.
     * <p>
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * This method add the given collidable to it's collection.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This method removes given collidable from the collidables.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * assuming the object is moving from trajectory.start() to trajectory.end().
     * This method checks for each collidable,
     * if the obeject has a possible collusion with them,
     * if there is, check if it is the closest, and return the closest collusion as
     * a sprites.CollisionInfo object.
     * if the object will not collide with any of the collidables
     * in this collection, return null.
     * <p>
     *
     * @param trajectory = the object line representing its corse from current start to end.
     * @return the closest collusion as a sprites.CollisionInfo object,
     * or if the object will not collide with any of the collidables
     * in this collection, return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        Point closestCollusionPoint = null;
        Collidable closestColadible = null;
        /**
         * for each collidable check if its there is intersection,
         * and if there is a hit.
         */
        for (int i = 0; i < this.collidables.size(); i++) {
            Rectangle currBlockRect = this.collidables.get(i).getCollisionRectangle();
            Point inter = trajectory.closestIntersectionToStartOfLine(currBlockRect);
            if (inter != null) {
                closestCollusionPoint = inter;
                closestColadible = this.collidables.get(i);
                break;
            }
        }
        //if there is no possible collusion, return null.
        if (closestCollusionPoint == null) {
            return null;
        }
        Point objectStart = trajectory.start();
        /**
         * for each collidable object check if its there is intersection,
         * and if there is a hit, check if it is the closest.
         */
        for (int i = 1; i < this.collidables.size(); i++) {
            Rectangle currBlockRect = this.collidables.get(i).getCollisionRectangle();
            Point inter = trajectory.closestIntersectionToStartOfLine(currBlockRect);
            //if there is a possible hit, check if it is closer then current closest.
            if (inter != null) {
                if (objectStart.distance(inter) < objectStart.distance(closestCollusionPoint)) {
                    closestCollusionPoint = inter;
                    closestColadible = this.collidables.get(i);
                }
            }
        }
        //create and return the closest collusion info
        CollisionInfo collusion = new CollisionInfo(closestCollusionPoint, closestColadible);
        return collusion;
    }
}