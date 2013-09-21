package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tank extends MovableItem implements ISteerable {

    private int armorStrength;
    private int missleCount;

    public Tank()  {
        this.armorStrength = 10;
        this.missleCount = 10;
    }

    public int getArmorStrength() {
        return armorStrength;
    }

    public void modifyArmorStrength(int val) {
        this.armorStrength += val;
    }

    public int getMissleCount() {
        return missleCount;
    }

    public void modifyMissleCount(int val) {
        this.missleCount += val;
    }

    public void fireMissle() {
        if (this.missleCount > 0){
            Missle a = new Missle(this);
            this.missleCount -= 1;
        }else{
            System.out.println("You are out of missles");
        }

    }

    public void modifySpeed(Boolean i){
        int tmpSpeed = 0;
        if (i){
            tmpSpeed = this.getSpeed() + 1;
        }else{
            tmpSpeed = this.getSpeed() - 1;
        }
        this.setSpeed(tmpSpeed);

    }

    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
