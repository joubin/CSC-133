package a4;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/1/13
 * Time: 4:46 PM
 */
public abstract class Vehicle extends MovableItem implements ISteerable {
    /*
    Added a Vehicle class as collection of Streeable objects.
     */
    public Vehicle(int direction) {
        super(direction);

    }

    public void steer(int direction) {
        /*
        change direction. Only some movable object can change their course.
         The commented out section below further insures that rule.

         However it is not a requirement as long as sub classes are properly implemented.
         */
//        if(this instanceof ISteerable){
            setDirection(getDirection() + direction);

//        }
    }
}
