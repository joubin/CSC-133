package a3;

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
}