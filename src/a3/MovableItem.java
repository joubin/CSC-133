package a3;

/**
 * User: joubin
 * Date: 9/19/13
 * Time: 11:43 PM
 */
public abstract class MovableItem extends GameObject {
    /*
    Movable game objects
     */
    private int direction;  // Direction of the object
    private int speed;  // Speed of the object
    private int health; // Health of the object
    private int lastTimeTick;

    public double getPrevx() {
        return prevx;
    }

    public double getPrevy() {
        return prevy;
    }

    private double prevx = 200;
    private double prevy = 200;

    public MovableItem(int direction) {
        /*
        All objects have to have a direction when first constructed.
         */
        super();
        this.direction = direction;
    }

    public int getHealth() {
        /*
        returns the health of each object.

        Different objects have different health parameters.
         */
        return health;
    }

    public void setHealth(int health) {
        /*
        Increase or decrease the health.

        Increase: increase the object obtains a lifepack or a boost
        DecreaseL decrease if the object is hit or is part of its lifecycle.
         */
        this.health += health;
    }


    public int getSpeed() {
        /*
        returns the speed of the objects. All movable objects have a starting speed.
         */
        return speed;
    }

    public void setSpeed(int speed) {
        /*
        set the speed of the object
         */
        this.speed = speed;
    }


    public int getDirection() {
        /*
        Return the direction of the item
         */
        return direction;
    }

    public void setDirection(int direction) {
        /*
        set a new direction for a given object
         */
        this.direction += direction;
        this.direction = (direction % 360);
        resetRotation();
        this.rotate(this.direction);
//        this.direction = ((direction % 360 + 360) % 360);
    }



    public void move(int time) {
        /*
        This move method properly moves the objects given the correct direction and speed
         */
        if (lastTimeTick == 0){
            lastTimeTick = time;
        }
        prevx = getX();
        prevy = getY();
        float newx = (float) Math.sin(Math.toRadians(direction)) * speed * (time-lastTimeTick);
        float newy = (float) Math.cos(Math.toRadians(direction)) * speed * (time-lastTimeTick);
        newx += getX();
        newy += getY();
        if (this instanceof Grenade)
            System.out.println("x:"+newx+" Y:"+newy+" Time:"+time);
//            setX(newx);
//            setY(newy);
        setLocation(newx, newy);

        lastTimeTick = time;
    }

    public void update(int time) {
        /*
        added for ease of use.

        Could call move() directly instead.

        However, the word move does not fully collect all of the tasks that need to take place at each
        game tick.
         */
        move(time);
        if (health < 1) {
            setShoulddie();
        }

    }


}
