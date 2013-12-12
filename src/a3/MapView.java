package a3;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

/**
 * User: joubin
 */
public class MapView extends JPanel implements IObserver {
    private GameWorldProxy gwp;
    private AffineTransform worldToND, ndToScreen, theVTM ;
    private int height = 700;
    private int width = 700;
    private int topRightY = 700;
    private int topRightX = 700;
    private int bottomLeftX = 0;
    private int bottomLeftY = 0;

    public MapView(GameWorldProxy gameWorldInstance) {
        this.gwp = gameWorldInstance;
        this.setBorder(new TitledBorder("Map"));
        this.setPreferredSize(new Dimension(700, 700));


        bottomLeftX = 0;
        bottomLeftY = 0;
        worldToND = new AffineTransform();
        ndToScreen = new AffineTransform();

    }
    private void setHW(){
        height = this.getHeight();
        width = this.getWidth();
        topRightY = height;
        topRightX = width;
        bottomLeftX = width-topRightX;
        bottomLeftY = height-topRightY;
        System.out.println( topRightX + " " + topRightY + " " + bottomLeftY +" " +bottomLeftX);
    }
    @Override
    public void update(IObservable o, Object arg) {
        gwp = (GameWorldProxy) o;
        this.repaint();
    }

    private void setWorldToND(){
        worldToND.setToIdentity();
        worldToND.scale(1/(topRightX-bottomLeftX), 1/(topRightY-bottomLeftY));
        worldToND.translate(-bottomLeftX, -bottomLeftY);

    }

    private void setNdToScreen(){
        ndToScreen.setToIdentity();
        ndToScreen.translate(0,height);
        ndToScreen.scale(width, -height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setHW();
        AffineTransform base = g2d.getTransform();
        setWorldToND();
        setNdToScreen();
        theVTM = (AffineTransform) ndToScreen.clone();
        theVTM.concatenate(worldToND);
        g2d.transform(theVTM);
        GameObjectCollection localGameObjectCollection;
        localGameObjectCollection = gwp.getGameWorldObjects();
        Iterator iterator = localGameObjectCollection.iterator();
        while (iterator.hasNext()) {
            GameObject tmp = (GameObject) iterator.next();
            tmp.draw(g2d);
        }
        g2d.setTransform(base);
    }

    public void select(int x, int y, boolean ctrl) {
        GameObjectCollection go = gwp.getGameWorldObjects();
        Iterator itr = go.iterator();
        if (!ctrl) {
            while (itr.hasNext()) {
                GameObject obj = (GameObject) itr.next();
                if (obj instanceof ISelectable) {
                    ((ISelectable) obj).setSelected(false);
                    this.repaint();
                }
            }
        }
        itr = go.iterator();
        while (itr.hasNext()) {
            GameObject obj = (GameObject) itr.next();
            if (obj instanceof ISelectable) {
                if (((ISelectable) obj).contains(x, y)) {
                    ((ISelectable) obj).setSelected(true);
                    this.repaint();
                }
            }
        }

    }

}
