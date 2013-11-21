package a2;

/**
 * Created with IntelliJ IDEA.
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

    public MovableItem(int direction) {
        /*
        All objects have to have a direction when first constructed.
         */
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

    private void setDirection(int direction) {
        /*
        set a new direction for a given object
         */
        this.direction = ((direction % 360 + 360) % 360);
    }

    public void mChangeDirection(int direction) {
        /*
        change direction. Only some movable object can change their course.
         The commented out section below further insures that rule.

         However it is not a requirement as long as sub classes are properly implemented.
         */
//        if(this instanceof ISteerable){
        if (direction % 5 == 0) {
            setDirection(getDirection() + direction);
        } else {
            int d = getDirection() - direction;
            setDirection(getDirection() - (direction % 5));
        }
//        }
    }

    public void move() {
        /*
        This move method properly moves the objects given the correct direction and speed
         */
        float newx = (float) Math.sin(Math.toRadians(direction)) * speed;
        float newy = (float) Math.cos(Math.toRadians(direction)) * speed;
        newx += getX();
        newy += getY();
        setX(newx);
        setY(newy);
    }

    public void update() {
        /*
        added for ease of use.

        Could call move() directly instead.

        However, the word move does not fully collect all of the tasks that need to take place at each
        game tick.
         */
        move();
        if (health < 1){
            setShoulddie();
        }

    }


}
