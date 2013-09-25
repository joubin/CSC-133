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
    private boolean blocked = false;

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

    public boolean fireMissle() {
        if (missileCount >= 0){
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
            this.setSpeed(tmpSpeed);
        }

    }

    public void setDirection(int direction){
        if (getBlockStatus()){
            toggleBlocked();
            super.setDirection(direction);
        }else{
            super.setDirection(direction);
        }
    }

    public String toString(){
        return String.format("%s speed=%d heading=%d armor=%d missile=%d", super.toString(), getSpeed(), getDirection(), armorStrength, missileCount);
    }


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
