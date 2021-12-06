package geometry;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a geometry.Point object with x and y values.
 * <p>
 */
public class Point {
    //data
    private double x;
    private double y;
    private static final double EPSILON = 0.0000001;
    // constructor

    /**
     * The geometry.Point constructor
     * <p>
     * This method adds all the given values to the geometry.Point variables.
     * <p>
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculates the distance of this point to the other point.
     * <p>
     *
     * @param other the other point to check distance
     * @return distance = the distance of this point to the other point.
     */
    public double distance(Point other) {
        double x1 = this.x;
        double x2 = other.getX();
        double y1 = this.y;
        double y2 = other.getY();
        double distance = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        distance = Math.sqrt(distance);
        return distance;
    }


    /**
     * This method checks and return true is the points are equal, false otherwise.
     * <p>
     *
     * @param other the other point to check equality
     * @return distance = true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.max(this.y, other.getY()) - Math.min(this.y, other.getY()) <= EPSILON
                && Math.max(this.x, other.getX()) - Math.min(this.x, other.getX()) <= EPSILON;
    }

    // Return the x and y values of this point

    /**
     * This method gets the x value of the geometry.Point.
     * <p>
     *
     * @return length = the x value of the geometry.Point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method gets the y value of the geometry.Point.
     * <p>
     *
     * @return length = the y value of the geometry.Point.
     */
    public double getY() {
        return this.y;
    }
}