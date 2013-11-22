package a2;

import java.awt.*;

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
    private Tank missileOwner;
    public Missile(Tank t) {
        super(t.getDirection());
        missileOwner = t;
        setHealth(100);
        this.setSpeed(t.getSpeed() + 5);
        setX((int)t.getDeltaX());
        setY((int)t.getDeltaY());
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
        return String.format("Missile=> %s speed=%d heading=%d" , super.toString(), this.getSpeed(), this.getDirection());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
//        int [] x = {(int) getX()+10,(int) getX()+22, (int) getX()+33};
//        int [] y = {(int) getY()+11,(int) getY()+22, (int) getY()+33};
//        g.fillPolygon(x, y, 3);
        g.fillOval((int) getX(), (int) getY(), 5,5);
    }



    @Override
    public void handleCollision(ICollider otherObject) {
        this.setShoulddie();
        this.setHealth(-1*getHealth());
    }

    @Override
    public int getSize() {
        return 5;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Tank getMissileOwner() {
        return missileOwner;
    }
}
