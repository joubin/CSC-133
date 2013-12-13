package a4;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * User: joubin
 */
public class Grenade extends Missile{
    private Circle center;
    private Triangle t1,t2,t3,t4;
    private int rotationAmount = 0;
    public Grenade(Tank t) {
        super(t);
        center = new Circle();
        t1 = new Triangle(0, 0, 0, getScale());
        t2 = new Triangle(0, 0, 90, getScale());
        t3 = new Triangle(0, 0, 180, getScale());
        t4 = new Triangle(0, 0, 270, getScale());
        System.out.println(getX() + " " + getY());
    }


    public void draw(Graphics2D g){
//        System.out.println("X:"+getX()+" Y:"+getY());
        AffineTransform save =  g.getTransform();
        rotate(rotationAmount++);
        g.transform(getTranslation());
        g.transform(getScale());
        g.transform(getRotation());
        t1.draw(g);
        t2.draw(g);
        t3.draw(g);
        t4.draw(g);
        center.draw(g);

        g.setTransform(save);
    }
    private class Circle{
        public void draw(Graphics2D g){
            g.setColor(Color.RED);
            g.fillOval(-3, -3, 6, 6);
        }


    }

    private class Triangle{
        private AffineTransform rotate = new AffineTransform();
        private AffineTransform scale = new AffineTransform();
        private AffineTransform translation = new AffineTransform();
        public Triangle(double x, double y, double degrees, AffineTransform scale){
            rotate.setToIdentity();
            rotate.rotate(Math.toRadians(degrees));
            translation.setToIdentity();
            translation.translate(x,y);
            this.scale = scale;


        }
        private int height = 1;
        public void draw(Graphics2D g){
            AffineTransform save = g.getTransform();
            g.transform(translation);
            g.transform(scale);
            g.transform(rotate);
            height = 1+(height+1)%10;
            int[] x = {-3, 3, 0};
            int[] y = {0, 0, height};
            g.setColor(Color.BLUE);
            g.fillPolygon(x, y, 3);
            g.setTransform(save);
        }

    }
}
