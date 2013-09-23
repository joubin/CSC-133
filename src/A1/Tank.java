package A1;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tank extends MovableItem implements ISteerable {

    private int armorStrength;
    private int missileCount;

    public Tank(float x, float y)  {
        this.armorStrength = 10;
        this.missileCount = 10;
        Random random = new Random(1024);
        setX(x);
        setY(y);

    }

    public int getArmorStrength() {
        return armorStrength;
    }

    public void modifyArmorStrength(int val) {
        this.armorStrength += val;
    }

    public int getMissileCount() {
        return missileCount;
    }

    public void modifyMissleCount(int val) {
        this.missileCount += val;
    }

    public void fireMissle() {
        if (this.missileCount > 0){
            Missle a = new Missle(this);
            this.missileCount -= 1;
        }else{
            System.out.println("You are out of missles");
        }

    }

    public void modifySpeed(int i){
        int tmpSpeed = 0;

        tmpSpeed = this.getSpeed() + i;

        this.setSpeed(tmpSpeed);

    }

    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
