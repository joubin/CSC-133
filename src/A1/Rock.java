package A1;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rock extends LandscapeItem {
    private int width, height;


    public Rock(float x, float y) {
        Random randGenrator = new Random();
        this.width = randGenrator.nextInt(21);
        this.height = randGenrator.nextInt(21);
        setX(x);
        setY(y);

    }
    public String toString(){
        return String.format("Rock=> %s width=%d height=%d", super.toString(), width, height);
    }
}
