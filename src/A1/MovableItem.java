package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:43 PM
 */
public abstract class MovableItem extends GameObject {
    private int direction;
    private int speed;
    private int health;

    public MovableItem(int direction){
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health += health;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getDirection() {
        return direction;
    }

    private void setDirection(int direction){
        this.direction = ((direction % 360 + 360) % 360);
    }

    public void mChangeDirection(int direction) {
        if(this instanceof ISteerable){
            if (direction % 5 == 0) {
                setDirection(getDirection() + direction);
            } else {
                int d = getDirection() - direction;
                setDirection(getDirection() - (direction%5));
            }
        }
    }

    public void move() {
        float newx = (float) Math.sin(direction) * speed;
        float newy = (float) Math.cos(direction) * speed;
        newx += getX();
        newy += getY();
        setX(newx);
        setY(newy);
    }

    public void update(){
        move();
    }


}
