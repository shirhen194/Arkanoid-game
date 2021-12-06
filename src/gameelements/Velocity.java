package gameelements;

import geometry.Point;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */


/**
 * This class generates a gameElements.Velocity object with a dx, dy for size of it.
 * gameElements.Velocity specifies the change in position on the `x` and the `y` axes.
 * <p>
 */
public class Velocity {
    private double dx;
    private double dy;
    // constructor

    /**
     * The gameElements.Velocity constructor
     * <p>
     * This method adds all the given values to the gameElements.Velocity variables.
     * <p>
     *
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method gets the dx value of the velocity.
     * <p>
     *
     * @return length = the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method gets the dy value of the velocity.
     * <p>
     *
     * @return length = the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * This method takes a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * <p>
     *
     * @param p the point to apply as new
     * @return returnPoint the point to return as new
     */
    public Point applyToPoint(Point p) {
        //add the velocity values to the new point created
        Point pNew = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return pNew;
    }

    /**
     * This method generates a velocity by angle and speed specified
     * by generating the dx and dy of the velocity bhy using trigonometry rules.
     *
     * @param angle the angle of the object speed.
     * @param speed the speed of the object.
     * @return velocity the new gameElements.Velocity according to angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //generate the dx and dy of the velocity bhy using trigonometry rules
        double dy = Math.sin(Math.toRadians(-angle)) * speed;
        double dx = Math.cos(Math.toRadians(angle)) * speed;;
        return new Velocity(dx, dy);
    }
}