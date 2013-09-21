package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MovableItem extends GameObject {
    private int direction;
    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if (direction % 5 == 0) {
            this.direction += direction;
        } else {
            this.direction -= direction % 5;
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


}
