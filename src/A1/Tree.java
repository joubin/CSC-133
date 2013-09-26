package A1;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:10 AM
 */
public class Tree extends LandscapeItem {
    private int rad;

    public Tree(float x, float y) {
        Random randGenrator = new Random();
        this.rad = randGenrator.nextInt(21);
        setX(x);
        setY(y);
    }

    public String toString(){
        return String.format("Tree=> %s diameter=%d", super.toString(), rad);
    }
}
