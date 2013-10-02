package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/1/13
 * Time: 4:59 PM
 */
public abstract class Projectile extends MovableItem{

    public Projectile(int direction) {
        super(direction);

    }

    public void mChangeDirection(int direction) {
        // Does nothing because missiles should not be able to change direction
    }

}
