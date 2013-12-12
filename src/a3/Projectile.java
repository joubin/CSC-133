package a3;

/**
 * User: joubin
 * Date: 10/1/13
 */
public abstract class Projectile extends MovableItem {
    /*
    Added a projectile class as a collection of objecst that do not inherent the ability to steer.
     */

    public Projectile(int direction) {
        super(direction);

    }

    public void steer(int direction) {
        /*
        Overwrote the steer so that only vehicals can change direction
         */
        // Does nothing because missiles should not be able to change direction
    }


}
