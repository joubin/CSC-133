package A1;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:13 AM
 */
public class Tank extends MovableItem implements ISteerable {

    private int health;
    private int missileCount;
    private boolean blocked = false;
    private boolean isPlayer = false;
    private String name ="Tank";

    public Tank(float x, float y)  {
        super(0);
        this.health = 10;
        this.missileCount = 10;
        Random random = new Random(1024);
        setX(x);
        setY(y);

    }

    public Tank(float x, float y, boolean myTank)  {
        super(0);
        this.health = 10;
        this.missileCount = 10;
        this.isPlayer = true;
        Random random = new Random(1024);
        setX(x);
        setY(y);
        if(myTank) name = "My Tank";

    }


    public int getHealth() {
        return health;
    }

    public void modifyArmorStrength(int val) {
        this.health += val;
    }

    public int getMissileCount() {
        return missileCount;
    }

    public void modifyMissleCount(int val) {
        this.missileCount += val;
    }

    public boolean fireMissle() {
        if (missileCount > 0){
            return true;
        }
        return false;

    }

    public void toggleBlocked(){
        this.blocked = !this.blocked;
    }

    public boolean getBlockStatus(){
        return this.blocked;
    }

    public void modifySpeed(int i){
        if(getBlockStatus()) {
            System.out.println("You are blocked");
        }else{
            int tmpSpeed = 0;
            tmpSpeed = this.getSpeed() + i;
            if(tmpSpeed >= 0) this.setSpeed(tmpSpeed);
        }

    }

    @Override
    public void changeDirection(int direction){
        mChangeDirection(direction);
        }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return String.format("%s=> %s speed=%d heading=%d armor=%d missile=%d",name, super.toString(), getSpeed(), getDirection(), health, missileCount);
    }


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
