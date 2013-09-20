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


    public Rock() {
        Random randGenrator = new Random();
        this.width = randGenrator.nextInt(21);
        this.height = randGenrator.nextInt(21);

    }
}
