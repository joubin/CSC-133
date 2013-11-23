package a3;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 10:55 AM
 */
public abstract class GameObject implements IDrawable, ICollider {

    private float x, y; // All game objects have an x and y coordinates
    Random myRandom = new Random(); // A random number generator that will be used to set the inital positions of x and y
    /*
    For each item, get a random RGB value and create a color out of it using the color class.
     */
    private float red = myRandom.nextFloat();
    private float green = myRandom.nextFloat();
    private float blue = myRandom.nextFloat();
    private boolean shouldDie = false;
    private int rad;

    private Color myColor = new Color(red, green, blue);


    /*
    Returns the X location of an item.
     */
    public float getX() {
        return x;
    }

    /*
    Setting the x location. The formula below ensures that the the range is always positive
     */
    public void setX(float x) {
        this.x = ((x % 700 + 700) % 700);

    }

    /*
    Returns the y location of an item.
    */
    public float getY() {
        return y;
    }

    /*
    Setting the y location. The formula below ensures that the the range is always positive
     */
    public void setY(float y) {
        this.y = ((y % 700 + 700) % 700);
    }

    /*
    Returns the color of the given object
     */
    public Color getMyColor() {
        return myColor;
    }

    /*
    To string that all objects have in common.
    Returns a string consiting of the location, and color of the object
     */
    public String toString() {
        return String.format("loc=[%.2f,%.2f] color=[%s,%s,%s]", x, y, myColor.getRed(), myColor.getGreen(), myColor.getBlue());
    }

    public void setShoulddie() {
        this.shouldDie = true;
    }

    public boolean getShoudDie() {
        return this.shouldDie;
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public boolean collidesWith(ICollider otherObject) {
        boolean result = false;
        int thisCenterX = (int) this.getX(); // find centers int thisCenterY = this.yLoc + (OBJECT_SIZE/2);
        int thisCenterY = (int) this.getY(); // find centers int thisCenterY = this.yLoc + (OBJECT_SIZE/2);
        GameObject obj = (GameObject) otherObject;
        int otherCenterX = (int) obj.getX();
        int otherCenterY = (int) obj.getY();
// find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = ((dx * dx) + (dy * dy));
        // find square of sum of radii
        int thisRadius = getSize();
        int otherRadius = obj.getSize();
        int radiiSqr = (int) Math.pow(thisRadius + otherRadius, 2);
        if (distBetweenCentersSqr <= radiiSqr) {
            result = true;
        }

        return result;

    }
}
