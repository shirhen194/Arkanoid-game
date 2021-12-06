package geometry;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

import java.util.ArrayList;

/**
 * This class generates a geometry.Line object with starting point and ending point.
 * <p>
 */
public class Line {
    //data
    private Point start;
    private Point end;
    private double slope;
    // constructors

    /**
     * The geometry.Line constructor
     * <p>
     * This method adds all the given values to the line variables.
     * <p>
     *
     * @param start the starting point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = calculateSlope();
    }

    /**
     * The geometry.Line constructor.
     * <p>
     * This method adds all the given values to the line variables.
     * <p>
     *
     * @param x1 the x of the starting point of the line
     * @param y1 the y of the starting point of the line
     * @param x2 the x of the ending point of the line
     * @param y2 the y of the ending point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        //enter the smaller value of x point as the starting point
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
        //generate slope
        this.slope = calculateSlope();
    }

    /**
     * This method gets the length value of the line.
     * <p>
     *
     * @return length = the length value of the line.
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * This method gets middle point of the line.
     * <p>
     *
     * @return middle = the middle point of the line.
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(xMid, yMid);
        return middle;
    }

    /**
     * This method check and return the slope of the line.
     * <p>
     *
     * @return slope = the slope of the line.
     */
    public double calculateSlope() {
        //return calculated slope by the formula
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * This method gets the start point of the line.
     * <p>
     *
     * @return start = the slope of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method gets the end point of the line.
     * <p>
     *
     * @return end = the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method gets the slope of the line.
     * <p>
     *
     * @return slope = the end point of the line.
     */
    public double getLineSlope() {
        return this.slope;
    }

    /**
     * This method gets the b value of the mx+b=y of the line.
     * <p>
     *
     * @return bValue = the b value of the mx+b=y of the line.
     */
    public double getBValue() {
        //return calculated b value by the formula
        return this.start().getY() - (this.getLineSlope() * this.start().getX());
    }

    /**
     * This method returns true if the lines intersect, false otherwise.
     * by calling intersectionWith.
     * <p>
     *
     * @param other = the other line to check intersection
     * @return isIntersecting = true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //if it's the same line
        if (this.equals(other)) {
            return true;
        }

        //if the lines are parellal and overlap
        if (this.getLineSlope() == other.getLineSlope()) {
            //if both of the lines are vertical
            if (this.start().getX() == this.end().getX() && other.start().getX() == other.end().getX()) {
                /*
                find the intersection(if exists) by calling intersectionWith function
                */
                Point intersection = intersectionWith(other);
                if (intersection != null) {
                    return true;
                }
                return false;
            }
            if (this.isLineInOther(other) || other.isLineInOther(this)) {
                return true;
            }
        }

        /*
        find the intersection(if exists) by calling intersectionWith function
         */
        Point intersection = intersectionWith(other);
        //if the intersection exists then it won't be equal to null
        if (intersection != null) {
            return true;
        }
        return false;
    }

    /**
     * This method checks and returns true if other line is in line,
     * false otherwise.
     * <p>
     *
     * @param other = the other line to check intersection
     * @return isLineInOther = true if other line is in line, false otherwise.
     */
    public boolean isLineInOther(Line other) {
        //check if other is inside this
        if (Math.min(other.start().getX(), other.end().getX()) >= Math.min(this.start.getX(), this.end.getX())) {
            //if the max is smaller then the max in this
            if (Math.max(other.start().getX(), other.end().getX()) <= Math.max(this.start.getX(), this.end.getX())) {
                return true;
            }
            //if it is only partly shared
            if (other.start().getX() != other.start().getX()) {
                return true;
            }

            //if the max is smaller then the min in other also, and other is not a point
            if (Math.min(other.start().getX(), other.end().getX()) <= Math.max(this.start.getX(), this.end.getX())) {
                if (other.start().getY() != other.end().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks and returns true if other line is in line,
     * false otherwise. for vertical lines.
     * <p>
     *
     * @param other = the other line to check intersection
     * @return isLineInOther = true if other line is in this line, false otherwise.
     */
    public boolean isLineInOtherVertical(Line other) {
        //check if other is inside this
        if (Math.min(other.start().getY(), other.end().getY()) > Math.min(this.start.getY(), this.end.getY())) {
            //if the max is smaller then the min in other also, and other is not a point
            if (Math.min(other.start().getY(), other.end().getY()) < Math.max(this.start.getY(), this.end.getY())) {
                if (other.start().getY() != other.end().getY()) {
                    return true;
                }
            }
            //if the max is bigger then the max in other also, and other is not a point
            if (Math.max(other.start().getY(), other.end().getY()) < Math.max(this.start.getY(), this.end.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks and returns true if the point is on the line,
     * false if not.
     * <p>
     *
     * @param point = the point to check if in line
     * @return isLineInOther = true if other line is in this line, false otherwise.
     */
    public boolean isPointInLine(Point point) {
        //check if the point is within the y range of the line
        if (point.getY() >= Math.min(this.start.getY(), this.end.getY())
                && point.getY() <= Math.max(this.start.getY(), this.end.getY())) {
            //check if the point is also within the x range of the line
            if (point.getX() >= Math.min(this.start.getX(), this.end.getX())
                    && point.getX() <= Math.max(this.start.getX(), this.end.getX())) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method checks and returns true if y is in line,
     * false otherwise.
     * <p>
     *
     * @param yIntersection the value of the y to check in range
     * @return isYInOther = true if Y is in line, false otherwise.
     */
    public boolean isInYRange(double yIntersection) {
        if (this.start.getY() < this.end.getY()) {
            if (this.start.getY() > yIntersection || this.end.getY() < yIntersection) {
                return false;
            }
        } else if (this.start.getY() < yIntersection || this.end.getY() > yIntersection) {
            return false;
        }
        return true;
    }

    /**
     * This method checks and returns true if x is in line,
     * false otherwise.
     * <p>
     *
     * @param xIntersection the value of the x to check in range
     * @return isYInOther = true if X is in line, false otherwise.
     */
    public boolean isInXRange(double xIntersection) {
        if (this.start.getX() < this.end.getX()) {
            if (this.start.getX() > xIntersection || this.end.getX() < xIntersection) {
                return false;
            }
        } else if (this.start.getX() < xIntersection || this.end.getX() > xIntersection) {
            return false;
        }
        return true;
    }

    /**
     * This method returns the intersection point if the lines intersect,
     * and null otherwise.
     * <p>
     *
     * @param other = the other line to check intersection
     * @return isIntersecting = the intersection point if the lines intersect,
     * * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //if it's the same line
        if (this.equals(other)) {
            return null;
        }
        double startX1 = this.start.getX();
        double startY1 = this.start.getY();
        double endX1 = this.end.getX();
        double endY1 = this.end.getY();
        double startX2 = other.start().getX();
        double startY2 = other.start().getY();
        double endX2 = other.end().getX();
        double endY2 = other.end().getY();
        //if both of the lines are vertical
        if (startX1 == endX1 && startX2 == endX2) {
            if (startX1 != startX2) {
                return null;
            }
            //if they are both lines
            //check if they overlap
            if (this.isLineInOtherVertical(other) || other.isLineInOtherVertical(this)) {
                return null;
            }
            if (startY1 == startY2 || startY1 == endY2) {
                Point intersection = new Point(startY1, startX1);
                return intersection;
            }
            if (endY1 == startY2 || endY1 == endY2) {
                Point intersection = new Point(endY1, startX1);
                return intersection;
            }
        }

        //if this line is vertical but other is not
        if (startX1 == endX1 && startX2 != endX2) {
            double xIntersection = startX1;

            /*
            check if the x of the intersection is in or out of the lines range
            */
            if (!this.isInXRange(xIntersection) || !other.isInXRange(xIntersection)) {
                return null;
            }
            //Linear equation: y - y0 = m(x - x0) = y = mx + y0 -mx0, b is y0 -mx0.
            double yIntersection = (other.getLineSlope() * xIntersection) + other.getBValue();

            /*
            check if the y of the intersection is out of the lines range
            */
            if (!this.isInYRange(yIntersection) || !other.isInYRange(yIntersection)) {
                return null;
            }
            Point intersection = new Point(xIntersection, yIntersection);
            return intersection;
        }
        //if other line is vertical but this is not
        if (startX1 != endX1 && startX2 == endX2) {
            double xIntersection = startX2;

            /*
            check if the x of the intersection is in or out of the lines range
            */
            if (!this.isInXRange(xIntersection) || !other.isInXRange(xIntersection)) {
                return null;
            }
            //Linear equation: y - y0 = m(x - x0) = y = mx + y0 -mx0, b is y0 -mx0.
            double b1 = this.getBValue();
            double yIntersection = (this.getLineSlope() * xIntersection) + b1;

            /*
            check if the y of the intersection is out of the lines range
            */
            if (!this.isInYRange(yIntersection) || !other.isInYRange(yIntersection)) {
                return null;
            }
            Point intersection = new Point(xIntersection, yIntersection);
            return intersection;
        }

        //check cases for included lines or parallel lines
        if (this.getLineSlope() == other.getLineSlope()) {
            if (this.isLineInOther(other) || other.isLineInOther(this)) {
                return null;
            }
            //if lines only connect in one point
            if (startY1 == endY1 && startY2 == endY2) {
                if (startY1 == startY2) {
                    if (startX1 == startX2 || startX1 == endX2) {
                        Point intersection = new Point(startX1, startY1);
                        return intersection;
                    }
                    if (endX1 == startX2 || endX1 == endX2) {
                        Point intersection = new Point(endX1, startY1);
                        return intersection;
                    }
                }
                return null;
            }
        }

        /*
         *get and compare the slopes of the two lines
         *if the two lines has the same slope, they will not intersect.
         */
        double slope1 = this.getLineSlope();
        double slope2 = other.getLineSlope();
        //get the slope differance, b values, and calculate the intersection.
        double slopeDifferance = slope1 - slope2;
        double xIntersection = (other.getBValue() - this.getBValue()) / slopeDifferance;

        /*
        check if the x of the intersection is in out of the lines range
         */
        if (!this.isInXRange(xIntersection) || !other.isInXRange(xIntersection)) {
            return null;
        }
        //get the y intersection
        double yIntersection = slope1 * xIntersection + this.getBValue();

        /*
        check if the y of the intersection is out of the lines range
         */
        if (!this.isInYRange(yIntersection) || !other.isInYRange(yIntersection)) {
            return null;
        }

        if (Double.isNaN(yIntersection) || Double.isNaN(xIntersection)) {
            return null;
        }
        //create intersection point
        Point intersection = new Point(xIntersection, yIntersection);
        return intersection;
    }

    /**
     * This method returns true if the lines are equal, false otherwise.
     * <p>
     *
     * @param other = the other line to check equality
     * @return isIntersecting = true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        //check and return if the start and end of both lines is the same
        //if they are both not points
        if ((this.start.getX() != this.end.getX() || this.start.getY() != this.end.getY())
                && (other.start.getX() != other.end.getX() || other.start.getY() != other.end.getY())) {
            //if they have the same x values in start and end
            if (this.start.getX() == other.start().getX() || this.start.getX() == other.end().getX()
                    && this.end.getX() == other.start().getX() || this.end.getX() == other.end().getX()) {
                //if they have the same y values in start and end
                if (this.start.getY() == other.start().getY() || this.start.getY() == other.end().getY()
                        && this.end.getY() == other.start().getY() || this.end.getY() == other.end().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns null if this line does not intersect with the rectangle,
     * otherwise, return the closest intersection point to the
     * start of the line.
     * <p>
     *
     * @param rect = the other line to check equality
     * @return intersectionPoint = point of intersection if the line intersects with
     * the rectangle, null otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        ArrayList<Point> intersectionPoints = (ArrayList<Point>) rect.intersectionPoints(this);
        // if there are no intersection points with the rectangle.
        if (intersectionPoints.isEmpty()) {  //case geometry.Line doesn't intersect geometry.Rectangle.
            return null;
        }
        Point closestPoint = intersectionPoints.get(0);
        for (int i = 0; i < intersectionPoints.size(); i++) {
            Point currPoint = intersectionPoints.get(i);
            if (this.start().distance(currPoint) < this.start().distance(closestPoint)) {
                closestPoint = intersectionPoints.get(i);
            }
        }
        return closestPoint;
    }
}