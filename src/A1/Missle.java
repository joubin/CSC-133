package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Missle extends MovableItem {
    private int direction, speed, lifetime;

    public Missle(Tank t) {
        this.lifetime = 5;
        this.speed = t.getSpeed();
        this.direction = t.getDirection();
    }
}
