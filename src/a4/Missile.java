package a4;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * User: joubin
 */
public class Missile extends Projectile {


    /*
        A missile is constructed and given the direction of the tank that fired it.

        It inherets the speed of the tank plus 5

        and it starts at the tanks x and y coordinates.

        Missile do not have the ability to change directions.
         */
    private Tank missileOwner;

    public Missile(Tank t) {
        super(t.getDirection());
        missileOwner = t;
        setHealth(200);
        this.setSpeed(t.getSpeed() + 5);
//        setX((int) t.getDeltaX());
//        setY((int) t.getDeltaY());
        setLocation(t.getX(), t.getY());
        rotate(getDirection());
        System.out.println(t.getX()+"  "+t.getY());
    }


    public void update(int time) {
        /*
        The update method of missile is uniq because after each game tick, the missile dies.

        Therefore, its update method ensures that that takes place.
        */
        super.move(time);
        setHealth(-1);
        if (getHealth() < 1){
            setShoulddie();
        }

    }


    public String toString() {
        /*
        Returns the parent class toString which is uniq to all objects and adds missile specific attributes.
         */
        return String.format("Missile=> %s speed=%d heading=%d", super.toString(), this.getSpeed(), this.getDirection());
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform save =  g.getTransform();

        super.draw(g);

        int[] x = {0 - 5, 0 + 5, 0};
        int[] y = {0 - 7, 0 - 7, 0 + 7};
        g.setColor(Color.RED);
        g.fillPolygon(x, y, 3);
        g.setTransform(save);

    }


    @Override
    public void handleCollision(ICollider otherObject) {
        if (otherObject instanceof Tank) {
            if (missileOwner == ((Tank) otherObject)) {
                steer(180);
                return;
            }
        }
        if (otherObject instanceof Missile) {
            if (((Missile) otherObject).getMissileOwner() == this.missileOwner) {
                return;
            }
        }
        if (this instanceof Plasma && otherObject instanceof LandscapeItem)
               return;
        this.setShoulddie();
        System.out.println("Missile hit " + otherObject);
        this.setHealth(-1 * getHealth());
    }

    @Override
    public int getSize() {
        return 15;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Tank getMissileOwner() {
        return missileOwner;
    }
}
