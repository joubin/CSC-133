package a3;

import java.awt.*;

/**
 * User: joubin
 */
public interface ISelectable {

    public void setSelected(boolean s);

    public boolean isSelected();

    public boolean contains(double x, double y);

    public void draw(Graphics2D g);
}
