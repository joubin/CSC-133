package a2;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:10 AM
 */
public class Tree extends LandscapeItem {
    /*
    Trees are landscape items that have a rad as their size attribute
     */
    private int rad;

    public int getRad() {
        /*
        returns the size attribute of tree.
         */
        return this.rad;
    }


    public Tree(float x, float y) {
        /*
        A tree is created at X and Y location provided by the game world.
        It then generates a random number to set as its size attribute.
         */
        Random randGenrator = new Random();
        this.rad = randGenrator.nextInt(21);
        setX(x);
        setY(y);
    }

    public String toString() {
        /*
        Appends the toString provided from the parent class -- all objects -- to Tree specific information
         */
        return String.format("Tree=> %s diameter=%d", super.toString(), this.rad);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval((int) getX(), (int) getY(), rad, rad);
    }
}
