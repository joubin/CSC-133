package a3;

import java.awt.*;

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
        setHealth(20);
        this.setSpeed(t.getSpeed() + 5);
        setX((int) t.getDeltaX());
        setY((int) t.getDeltaY());
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
        return String.format("Missile=> %s speed=%d heading=%d", super.toString(), this.getSpeed(), this.getDirection());
    }

    @Override
    public void draw(Graphics g) {
        int[] x = {(int) getX() - 10, (int) getX() + 10, (int) getX()};
        int[] y = {(int) getY() - 15, (int) getY() - 15, (int) getY() + 15};
        g.setColor(Color.RED);
        g.fillPolygon(x, y, 3);
//        g.fillOval((int) getX()-5/2, (int) getY()-5/2, 5,5);
    }


    @Override
    public void handleCollision(ICollider otherObject) {
        if (otherObject instanceof Tank) {
            if (missileOwner == ((Tank) otherObject)) {
                mChangeDirection(180);
                return;
            }
        }
        if (otherObject instanceof Missile) {
            if (((Missile) otherObject).getMissileOwner() == this.missileOwner) {
                return;
            }
        }
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
