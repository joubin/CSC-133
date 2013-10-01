package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:34 AM
 */
public class Missile extends MovableItem {

    public Missile(Tank t) {
        super(t.getDirection());
        setHealth(5);
        this.setSpeed(t.getSpeed()+5);
        setX(t.getX());
        setY(t.getY());
    }


    public void update(){
        super.move();
        setHealth(-1);

    }


    public String toString(){
        return String.format("Missile=> %s speed=%d heading=%d ", super.toString(), this.getSpeed(), this.getDirection());
    }
}