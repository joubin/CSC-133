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
//    private AffineTransform worldToND, ndToScreen, theVTM ;
    private int height = 700;
    private int width = 700;
    private int topRightY = 700;
    private int topRightX = 700;
    private int bottomLeftX = 0;
    private int bottomLeftY = 0;
    private Point selectionStart = new Point(0,0);
    private Point selectionEnd = new Point(0,0);
    private AffineTransform viewTransform = new AffineTransform();
    private AffineTransform worldToND, ndToScreen, theVTM, inverseVTM;
    private double windowRight = 700, windowTop = 700, windowLeft = 0, windowBottom = 0;

    public MapView(GameWorldProxy gameWorldInstance) {
        this.gwp = gameWorldInstance;
        this.setPreferredSize(new Dimension(700, 700));


        bottomLeftX = 0;
        bottomLeftY = 0;
//        worldToND = new AffineTransform();
//        ndToScreen = new AffineTransform();

    }
    private void setHW(){
        height = this.getHeight();
        width = this.getWidth();
        topRightY = height;
        topRightX = width;
        bottomLeftX = width-topRightX;
        bottomLeftY = height-topRightY;
    }


    protected void zoomIn(){
        double h = windowTop - windowBottom;
        double w = windowRight - windowLeft;

        windowLeft += w*0.05;
        windowRight -= w*0.05;
        windowTop -= h*0.05;
        windowBottom += h*0.05;
        this.repaint();
    }

    /**
     * method that will make the window smaller when it is invoked causing a zoom out to happen
     */
    protected void zoomOut(){
        double h = windowTop - windowBottom;
        double w = windowRight - windowLeft;

        windowLeft -= w*0.05;
        windowRight += w*0.05;
        windowTop += h*0.05;
        windowBottom -= h*0.05;
        this.repaint();
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


    public AffineTransform getViewTransform(){
        return (AffineTransform) viewTransform.clone();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setHW();
        Graphics2D g2d = (Graphics2D) g;
//        this.viewTransform.setToIdentity();
//        this.viewTransform.translate(0, this.getHeight());
//        this.viewTransform.scale(1, -1);

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
//        g.setColor(Color.BLACK);
//        try {
//            g.fillRect((int) selectionStart.getX(), (int) selectionStart.getY(),  (int)Math.abs(selectionStart.getX()-selectionEnd.getX()),(int) Math.abs(selectionStart.getY()-selectionEnd.getY()));
//
//        }catch (Exception e){
//            System.out.println("fail");
//        }
        g2d.setTransform(save);

    }

    public void setSelectionPoints(Point s, Point e){
        selectionStart = s;
        selectionEnd = e;
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
