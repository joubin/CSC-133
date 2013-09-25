package A1;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameObject {

    private float x, y;
    Random myRandom = new Random();
    private float red = myRandom.nextFloat();
    private float green = myRandom.nextFloat();
    private float blue = myRandom.nextFloat();

    private Color myColor = new Color(red, green, blue);



    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public Color getMyColor() {
        return myColor;
    }

    public String toString(){
        return String.format("loc=[%2f,%2f] color=%s", x,y,myColor.toString());
    }


}
