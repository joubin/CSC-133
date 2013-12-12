package a3;

import java.awt.*;

/**
 * User: joubin
 */
public interface ISelectable {

    public void setSelected(boolean s);

    public boolean isSelected();

    public boolean contains(int x, int y);

    public void draw(Graphics2D g);
}
