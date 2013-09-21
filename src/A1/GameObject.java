package A1;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/20/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameObject {

    private float x, y;
    private Color myColor = Color.white;


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


}
