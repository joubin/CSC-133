package a2;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/1/13
 * Time: 4:59 PM
 */
public abstract class Projectile extends MovableItem {
    /*
    Added a projectile class as a collection of objecst that do not inherent the ability to steer.
     */

    public Projectile(int direction) {
        super(direction);

    }

    public void mChangeDirection(int direction) {
        /*
        Overwrote the mChangeDirection so that only vehicals can change direction
         */
        // Does nothing because missiles should not be able to change direction
    }


}
