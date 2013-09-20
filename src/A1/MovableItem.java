package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MovableItem {

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if(direction%5 == 0){
            this.direction = direction;
        }else {
            System.out.println("You cant do that");
        }
    }

    private int direction;




}
