package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Missile extends MovableItem {
    private int direction, speed, lifetime;

    public Missile(Tank t) {
        this.lifetime = 5;
        this.speed = t.getSpeed();
        this.direction = t.getDirection();
    }

    public String toString(){
        return String.format("Missile=> %s speed=%d heading=%d ", super.toString(), getSpeed(), getDirection());
    }
}
