package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:34 AM
 */
public class Missile extends Projectile {
    /*
    A missile is constructed and given the direction of the tank that fired it.

    It inherets the speed of the tank plus 5

    and it starts at the tanks x and y coordinates.

    Missile do not have the ability to change directions.
     */

    public Missile(Tank t) {
        super(t.getDirection());
        setHealth(5);
        this.setSpeed(t.getSpeed() + 5);
        setX(t.getX());
        setY(t.getY());
    }


    public void update() {
        /*
        The update method of missile is uniq because after each game tick, the missile dies.

        Therefore, its update method ensures that that takes place.
        */
        super.move();
        setHealth(-1);

    }


    public String toString() {
        /*
        Returns the parent class toString which is uniq to all objects and adds missile specific attributes.
         */
        return String.format("Missile=> %s speed=%d heading=%d ", super.toString(), this.getSpeed(), this.getDirection());
    }
}
