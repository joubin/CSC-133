package a2;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:03 AM
 */
public class Rock extends LandscapeItem {
    /*
    rocks are landscape items with size attributes of width and height.
     */
    private int width, height;

    public int getWidth() {
        /*
        returns the width of the object
         */
        return width;
    }

    public int getHeight() {
        /*
        returns the height of the given object.
         */
        return height;
    }


    public Rock(float x, float y) {
        /*
        When a rock is generated, it is given a random width and height between 0 to 20.
         */
        Random randGenrator = new Random();
        this.width = randGenrator.nextInt(21);
        this.height = randGenrator.nextInt(21);
        setX(x);
        setY(y);

    }

    public String toString() {
        /*
        This tostring combines what has been returned by gameobject and adds Rock specified attributes.
         */
        return String.format("Rock=> %s width=%d height=%d", super.toString(), width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) getX() - width, (int) getY() - height, width, height);
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleCollision(ICollider otherObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
