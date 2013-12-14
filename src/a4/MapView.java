package a4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Iterator;

/**
 * User: joubin
 */
public class MapView extends JPanel implements IObserver {
    private GameWorldProxy gwp;

    private AffineTransform worldToND, ndToScreen, theVTM, inverseVTM;
    private double windowRight = 700;
    private double windowTop = 700;
    private double windowLeft = 0;
    private double windowBottom = 0;

    public MapView(GameWorldProxy gameWorldInstance) {
        this.gwp = gameWorldInstance;
        this.setPreferredSize(new Dimension(700, 700));
    }



    protected void pan(char x){
        switch (x){
            case 'u':
                windowTop += 10;
                windowBottom += 10;
                break;
            case 'd':
                windowTop -= 10;
                windowBottom -= 10;
                break;
            case 'l':
                windowLeft -= 10;
                windowRight -= 10;
                break;
            case 'r':
                windowLeft += 10;
                windowRight += 10;
                break;
            default:
                break;
        }

    }
    @Override
    public void update(IObservable o, Object arg) {
        gwp = (GameWorldProxy) o;
        this.repaint();
    }
    private AffineTransform buildScreenTransform(double width, double height) {
        AffineTransform result = new AffineTransform();
        result.setToIdentity();
        result.translate(0, height);
        result.scale(width, -height);
        return result;
    }


    private AffineTransform buildNDTransform(double windowWidth, double windowHeight, double windowLeft, double windowBottom) {
        AffineTransform result = new AffineTransform();
        result.setToIdentity();
        result.scale(1/windowWidth, 1/windowHeight);
        result.translate(-windowLeft, -windowBottom);
        return result;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        worldToND = buildNDTransform(windowRight-windowLeft,windowTop-windowBottom,windowLeft,windowBottom);
        ndToScreen = buildScreenTransform(this.getSize().getWidth(),this.getSize().getHeight());
        theVTM = (AffineTransform) ndToScreen.clone();
        theVTM.concatenate(worldToND);
        try {
            inverseVTM = theVTM.createInverse();
        } catch (NoninvertibleTransformException e) {
        }
        g2d.transform(theVTM);
        AffineTransform save = g2d.getTransform();

        GameObjectCollection localGameObjectCollection;
        localGameObjectCollection = gwp.getGameWorldObjects();
        Iterator iterator = localGameObjectCollection.iterator();
        while (iterator.hasNext()) {
            GameObject tmp = (GameObject) iterator.next();
            tmp.draw(g2d);
            g2d.setTransform(save);

        }
        g2d.setTransform(save);

    }

    public double getWindowHeight(){
        double returnAnswer = windowTop - windowBottom;
        return returnAnswer;
    }

    public double getWindowWidth(){
        double returnAnswer = windowRight - windowLeft;
        return returnAnswer;

    }

    protected void zoomIn(){
        double tempHeight = getWindowHeight();
        double tempWidth = getWindowWidth();

        windowLeft += tempWidth*0.05;
        windowRight -= tempWidth*0.05;
        windowTop -= tempHeight*0.05;
        windowBottom += tempHeight*0.05;
        this.repaint();
    }


    protected void zoomOut(){
        double tempHeight = getWindowHeight();
        double tempWidth = getWindowWidth();

        windowLeft -= tempWidth*0.05;
        windowRight += tempWidth*0.05;
        windowTop += tempHeight*0.05;
        windowBottom -= tempHeight*0.05;
        this.repaint();
    }

    public void select(Point pp, boolean ctrl) {
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
                Point2D.Float transformedPoints = new Point2D.Float();
                 inverseVTM.transform(pp, transformedPoints);
                if (((ISelectable) obj).contains(transformedPoints.getX(), transformedPoints.getY())) {
                    ((ISelectable) obj).setSelected(true);
                    this.repaint();
                }
            }
        }

    }

}
