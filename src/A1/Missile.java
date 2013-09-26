package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:34 AM
 */
public class Missile extends MovableItem {
    private int health;

    public Missile(Tank t) {
        setHealth(5);
        this.setSpeed(t.getSpeed()+5);
        this.setDirection(t.getDirection());
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
