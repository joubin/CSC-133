package a2;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:13 AM
 */
public class Tank extends Vehicle {

    private int missileCount;   // each tank has it own number of missiles
    private boolean blocked = false; // tanks can get blocked by other objects. The game world will let them know if so
    private boolean isPlayer = false; // special flag to differentiate the player tank from other tanks
    private String name = "Tank";
    protected IStrategy curStrategy = null;



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
        setX(x); // set x and y coordinates given by the gameworld
        setY(y);
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
        Random random = new Random(700);
        setX(x);
        setY(y);
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
        System.out.print(" SPEED SPEED SPEED SPEED SPEED SPEED ");

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
        mChangeDirection(direction);
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
    public void draw(Graphics g) {
        g.setColor(getMyColor());
        int width = 30;
        int height = 30;
        if (isPlayer) g.setColor(Color.BLUE);
        int localx = (int) getX() - (width/2);
        int localy = (int) getY() - (width/2);
        g.drawRect(localx, localy, width, width);
//        int sx[] = {(int) getX()-15, (int) getX(),(int) getX()+15};
//        int sy[] = {(int) getY()+15, (int) getY()+30, (int) getY()+15};
//        g.setColor(Color.BLACK);
//        g.fillPolygon( sx, sy, 3);
    }

    public void update() {

        super.update();
        if (curStrategy != null) {
            curStrategy.apply();
            System.out.println("\n=====> " + curStrategy.toString());
        }

    }



    @Override
    public void handleCollision(ICollider otherObject) {
        // I am a Tank
        GameObject tmp = (GameObject) otherObject;

        if (tmp instanceof Tank){
            ((Tank) this).toggleBlocked();
        }

        if (tmp instanceof Missile){
            this.setHealth(-1);
        }


    }

    @Override
    public int getSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
