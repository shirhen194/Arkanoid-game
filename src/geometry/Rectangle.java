package geometry;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Shir Hanono
 * @version 1.0 11/04/2021
 */

/**
 * This class generates a geometry.Rectangle object with starting point and ending point, and color.
 * <p>
 */
public class Rectangle {
    private Point start;
    private Point end;
    private Color color;
    private double width;
    private double height;
    private Point upperLeft;
    //bounds
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    //constructor

    /**
     * The geometry.Rectangle constructor
     * <p>
     * This method adds all the given values to the geometry.Rectangle variables.
     * <p>
     *
     * @param start the starting point of the geometry.Rectangle
     * @param end   the end point of the geometry.Rectangle
     * @param color the color of the geometry.Rectangle
     */
    public Rectangle(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.width = (int) (this.end.getX() - this.start.getX());
        //get the width and height of the rectangle considering the points value.
        if (this.start.getY() < this.end.getY()) {
            this.height = (int) (this.end.getY() - this.start.getY());
        } else {
            this.height = (int) (this.start.getY() - this.end.getY());
        }
        //set bounding lines
        this.setRectBounds();
    }

    /**
     * The geometry.Rectangle constructor
     * <p>
     * This method adds all the given values to the geometry.Rectangle variables.
     * <p>
     *
     * @param upperLeft the starting point of the geometry.Rectangle
     * @param width     the width of the geometry.Rectangle
     * @param height    the height of the geometry.Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.start = upperLeft;
        this.end = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        //set bounding lines
        this.setRectBounds();
    }

    /**
     * The geometry.Rectangle constructor
     * <p>
     * This method adds all the given values to the geometry.Rectangle variables.
     * <p>
     *
     * @param upperLeft the starting point of the geometry.Rectangle
     * @param width     the width of the geometry.Rectangle
     * @param height    the height of the geometry.Rectangle
     * @param color     the color of the geometry.Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.start = upperLeft;
        this.end = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = color;
        //set bounding lines
        this.setRectBounds();
    }

    /**
     * The geometry.Rectangle constructor.
     * <p>
     * This method adds all the given values to the geometry.Rectangle variables.
     * <p>
     *
     * @param x1    the x of the starting point of the geometry.Rectangle
     * @param y1    the y of the starting point of the geometry.Rectangle
     * @param x2    the x of the ending point of the geometry.Rectangle
     * @param y2    the y of the ending point of the geometry.Rectangle
     * @param color the color of the geometry.Rectangle
     */
    public Rectangle(double x1, double y1, double x2, double y2, Color color) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
        this.color = color;
        this.width = this.end.getX() - this.start.getX();
        this.height = this.end.getY() - this.start.getY();
        this.upperLeft = this.start;
        //set bounding lines
        this.setRectBounds();
    }

    /**
     * This method gets the right line of the geometry.Rectangle.
     * <p>
     *
     * @return right = the right line of the geometry.Rectangle.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * This method gets the left line of the geometry.Rectangle.
     * <p>
     *
     * @return left = the left line of the geometry.Rectangle.
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * This method gets the top line of the geometry.Rectangle.
     * <p>
     *
     * @return top = the top line of the geometry.Rectangle.
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * This method gets the bottom line of the geometry.Rectangle.
     * <p>
     *
     * @return bottom = the bottom line of the geometry.Rectangle.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * This method gets the start point of the geometry.Rectangle.
     * <p>
     *
     * @return start = the start point of the geometry.Rectangle.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * <p>
     * This method defines the rectangle bounding lines.
     * </p>
     */
    public void setRectBounds() {
        //create the rect edge points
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());

        //create and define the rectangle bounds
        this.left = new Line(this.upperLeft, bottomLeft);
        this.right = new Line(upperRight, bottomRight);
        this.top = new Line(this.upperLeft, upperRight);
        this.bottom = new Line(bottomLeft, bottomRight);
    }

    /**
     * <p>
     * This method draws the geometry.Rectangle on the given DrawSurface.
     * </p>
     *
     * @param surface = the DrawSurface object.
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(this.color);
        //draws on surface
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * <p>
     * This method Return a (possibly empty) List of intersection points
     * with the specified line.
     * </p>
     *
     * @param line = the line to check intersection points with.
     * @return array list of the intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] rectLines = new Line[]{this.left, this.right, this.top, this.bottom};
        //create the intersection points list
        ArrayList<Point> intersectionPoints = new ArrayList<>();
        for (int i = 0; i < rectLines.length; i++) {
            Point intersection = line.intersectionWith(rectLines[i]);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;
    }

    /**
     * <p>
     * this getter returns the width of the rectangle.
     * </p>
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * <p>
     * this getter returns the height of the rectangle.
     * </p>
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * <p>
     * this getter returns the upperLeft of the rectangle.
     * </p>
     *
     * @return the upperLeft of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * <p>
     * this getter returns the color of the rectangle.
     * </p>
     *
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
}

