package a2;

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

    public Tank(float x, float y) {
        /*
        Constructors 1:
            Used to generate tanks not the players
         */
        super(0); // set initial direction of the tank
        setHealth(10); // set the health
        this.missileCount = 10; // set missile count
        setX(x); // set x and y coordinates given by the gameworld
        setY(y);

    }

    public Tank(float x, float y, boolean myTank) {
        /*
        Special constructor unique for the player.
        allows
         */
        super(0);
        setHealth(10);
        this.missileCount = 10;
        this.isPlayer = true;
        Random random = new Random(1024);
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


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
