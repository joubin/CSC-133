package a3;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:13 AM
 */
public class Tank extends Vehicle implements ISelectable {

    private int missileCount;   // each tank has it own number of missiles
    private boolean blocked = false; // tanks can get blocked by other objects. The game world will let them know if so
    private boolean isPlayer = false; // special flag to differentiate the player tank from other tanks
    private String name = "Tank";
    protected IStrategy curStrategy = null;
    private int width = 30;
    private int height = 30;
    private int size = (int) ((int) Math.sqrt((width / 2 * width / 2) + (height / 2 * height / 2)));
    private double dy;
    private double dx;
    private Missile lastMissileToHit = null;
    private boolean isSelected;
    private int grenadeCount;


    public double getDeltaY() {
        return dy;
    }

    public double getDeltaX() {
        return dx;
    }

    private double deltaY;


    public void setCurStrategy(IStrategy curStrategy) {
        this.curStrategy = curStrategy;
    }


    public Tank(float x, float y, IStrategy strategy) {
        /*
        Constructors 1:
            Used to generate tanks not the players
         */
        super(0); // set initial direction of the tank

        setHealth(10); // set the health
        this.missileCount = 100; // set missile count
//        setX(x); // set x and y coordinates given by the gameworld
//        setY(y);
        setLocation(x, y);
        grenadeCount = 10;
        this.curStrategy = strategy;


    }

    public Tank(float x, float y, boolean myTank) {
        /*
        Special constructor unique for the player.
        allows
         */
        super(0);
        setHealth(10);
        this.missileCount = 100;
        this.isPlayer = true;
        setLocation(x, y);
        grenadeCount = 1000;

        if (myTank) name = "My Tank";

    }


    public void modifyArmorStrength(int val) {
        /*
        incase the tank is hit, it needs to loose
         */
        setHealth(val);
    }

    public int getMissileCount() {
        /*
        return missile count
         */
        return missileCount;
    }

    public void modifyMissleCount(int val) {
        /*
        gain or loose missiles
         */
        this.missileCount += val;
    }

    public boolean fireMissile() {
        /*
        fire a missile
         */
        if (missileCount > 0) {
            return true;
        }
        return false;

    }


    public boolean fireGrenade() {
        /*
        fire a missile
         */
        if (grenadeCount > 0) {
            return true;
        }
        return false;

    }
    public void toggleBlocked() {
        /*
        get blocked or unblocked
         */
        this.blocked = !this.blocked;
    }

    public boolean getBlockStatus() {
        /*
        return block status
         */
        return this.blocked;
    }

    public void modifySpeed(int i) {
        /*
        check to make sure youre not blocked before moving

        Increase and decrease speed
         */

        if (getBlockStatus()) {
            System.out.println("You are blocked");
        } else {
            int tmpSpeed = 0;
            tmpSpeed = this.getSpeed() + i;
            if (tmpSpeed >= 0) this.setSpeed(tmpSpeed);
            else System.out.println("You cant have negative speed");
        }
    }

    public void changeDirection(int direction) {
        /*
        steer the object by setting new direction
         */
        steer(direction);
    }

    public String getName() {
        /*
        return the name of this object
         */
        return this.name;
    }

    public String toString() {
        /*
        toString specific to tanks
         */
        return String.format("%s=> %s speed=%d heading=%d armor=%d missile=%d", name, super.toString(), getSpeed(), getDirection(), getHealth(), missileCount);
    }

    @Override
    public void setSelected(boolean s) {
        this.isSelected = s;
    }

    @Override
    public boolean isSelected() {
        return isSelected;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(double x, double y) {
        //Transform x with world conrdinates.
        if ((x > (getX() - width / 2)) && (y > (getY() - height / 2)) && (y < (getY() + height / 2)) && (x < getX() + width / 2)) {
            System.out.println(x + " " + y + " " + getX() + " " + getY());
            return true;
        } else {
            return false;
        }

    }



    @Override
    public void draw(Graphics2D g) {
        AffineTransform save =  g.getTransform();
        super.draw(g);

        int localx = -width/2;
        int localy = -height/2;

        if (isPlayer) g.setColor(Color.BLUE);
        if (isSelected) {
            g.fillRect(localx, localy, width, height);
        } else {
            g.drawRect(localx, localy, width, height);

        }
        int direction = this.getDirection();
//        g.drawOval((int) getX()-size/4, (int) getY()-size/4,(int) size/2, (int) size/2);
        g.setColor(Color.RED);
        g.drawOval(0 - (size / 2), 0 - (size / 2), (int) size, (int) size);
        dx = ((double) Math.cos(Math.toRadians(90 - direction)) * width);
        dy = ((double) Math.sin(Math.toRadians(90 - direction)) * height);
        g.setColor(Color.black);
        g.drawLine(0, 0, 0, 20);
        g.setTransform(save);

    }

    public void update(int time) {

        super.update(time);
        if (curStrategy != null) {
            curStrategy.apply();
            //System.out.println("\n=====> " + curStrategy.toString());
        }

    }


    @Override
    public void handleCollision(ICollider otherObject) {
        // I am a Tank
        GameObject tmp = (GameObject) otherObject;

        if (tmp instanceof Tank || tmp instanceof LandscapeItem) {
            this.changeDirection(this.getDirection() + 180);
//            this.modifySpeed(this.getSize());
//            setX(getPrevx());
//            setY(getPrevy());
            setLocation(getPrevx(), getPrevy());
            System.out.println("I hit a landscape item or another tree or another tank");
        }

        if (tmp instanceof Missile) {
            if (((Missile) tmp).getMissileOwner() != this && lastMissileToHit != tmp) {
                this.setHealth(-1);
                lastMissileToHit = (Missile) tmp;

            }
        }


    }

    @Override
    public int getSize() {
        return this.size;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getGrenadeCount() {
        return grenadeCount;
    }

    public void setGrenadeCount(int grenadeCount) {
        this.grenadeCount = grenadeCount;
    }


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
