package a2;

import java.awt.*;
import java.util.Random;

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
    private int size = (int) (2*Math.sqrt(2*Math.pow(15,2)));
    private double deltaX;
    private Missile lastMissileToHit = null;
    private boolean isSelected;

    public double getDeltaY() {
        return deltaY;
    }

    public double getDeltaX() {
        return deltaX;
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
    public void setSelected(boolean s) {
        this.isSelected = s;
    }

    @Override
    public boolean isSelected() {
        return isSelected;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean contains(int x, int y) {
        if ((x > (getX()-width/2)) &&  (y > (getY()-height/2)) && (y < (getY()+height/2)) && (x < getX()+width/2)){
            System.out.println(x+" "+y+" "+getX()+" "+getY());
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getMyColor());
        if (isPlayer) g.setColor(Color.BLUE);
        int localx = (int) getX() - (width/2);
        int localy = (int) getY() - (width/2);
        if (isSelected){
            g.fillRect(localx, localy, width, height);
        } else{
            g.drawRect(localx, localy, width, height);

        }
        int direction = this.getDirection();
        g.drawOval((int) getX()-size/4, (int) getY()-size/4,(int) size/2, (int) size/2);
        g.setColor(Color.RED);
        g.drawOval((int) getX()-size/2, (int) getY()-size/2,(int) size, (int) size);
        deltaX = ((double) Math.cos(Math.toRadians(90-direction)) * width) + getX();
        deltaY = ((double) Math.sin(Math.toRadians(90-direction)) * height) + getY();
        g.setColor(Color.black);
        g.drawLine((int) this.getX(), (int) this.getY(), (int) deltaX,(int) deltaY);


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

        if (tmp instanceof Tank || tmp instanceof LandscapeItem){

            this.changeDirection(this.getDirection()+180);
            System.out.println("I hit a tree or another tank");
        }

        if (tmp instanceof Missile){
            if (((Missile) tmp).getMissileOwner() != this && lastMissileToHit != tmp){
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


    // Make it so it can only fire a missle when it has missles to fire.
    // other wise it cant fire.
}
