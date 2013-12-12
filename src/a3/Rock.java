package a3;

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
        this.width = randGenrator.nextInt(21) + 10;
        this.height = randGenrator.nextInt(21) + 10;
//        setX(x);
//        setY(y);
        setLocation(x, y);
    }

    public String toString() {
        /*
        This tostring combines what has been returned by gameobject and adds Rock specified attributes.
         */
        return String.format("Rock=> %s width=%d height=%d", super.toString(), width, height);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.GRAY);
        g.fillRect(-width/2, -height / 2, width, height);
        g.setColor(Color.RED);
        int size = getSize();
    }


    @Override
    public void handleCollision(ICollider otherObject) {
        //collision for tank
        GameObject tmp = (GameObject) otherObject;

        if (tmp instanceof Tank) {
//            ((Tank) tmp).toggleBlocked();
            System.out.println("Just hit a tree" +
                    "just hit a tree" +
                    "just hit a tree" +
                    "ouch");
        }
        if (tmp instanceof Missile) {
            //Collision for Tank
            tmp.setShoulddie();
        }
    }

    @Override
    public int getSize() {
        int max = Math.max(width, height);
        max = (max / 2 * max / 2) + (max / 2 * max / 2);
        max = (int) Math.sqrt(max);
//        max = 2* max;
        return max;

    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
