package a4;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * User: joubin
 */
public class Plasma extends Missile{
    Point2D.Double[] controlPoint = new Point2D.Double[4];


    public Plasma(Tank t) {
        super(t);
        Random r = new Random();
        controlPoint[0] = new Point2D.Double(0,0);
        controlPoint[1] = new Point2D.Double(r.nextInt(20),r.nextInt(20));
        controlPoint[2] = new Point2D.Double(-r.nextInt(20),-r.nextInt(20));
        controlPoint[3] = new Point2D.Double(0,20);
    }

    public void draw(Graphics2D g){
        AffineTransform save =  g.getTransform();
        g.transform(getTranslation());
        g.transform(getScale());
        g.transform(getRotation());
        g.setColor(Color.BLACK);
        drawBezierCurve(this.controlPoint, 0, g);
        g.setColor(Color.RED);
        g.drawLine((int)controlPoint[0].getX(),(int) controlPoint[0].getY(),(int) controlPoint[1].getX(),(int) controlPoint[1].getY());
        g.drawLine((int)controlPoint[1].getX(),(int) controlPoint[1].getY(),(int) controlPoint[2].getX(),(int) controlPoint[2].getY());
        g.drawLine((int)controlPoint[2].getX(),(int) controlPoint[2].getY(),(int) controlPoint[3].getX(),(int) controlPoint[3].getY());
        g.setTransform(save);

    }

    private void drawBezierCurve (Point2D.Double[] controlPointVector, int level, Graphics2D g) {
        Point2D.Double[] LeftSubVector = new Point2D.Double[4];
        Point2D.Double[] RightSubVector = new Point2D.Double[4];
        if ( straightEnough (controlPointVector) || (level>15) )
            g.drawLine((int)controlPointVector[0].getX(),(int) controlPointVector[0].getY(),(int) controlPointVector[3].getX(), (int)controlPointVector[3].getY());
        else {
            subdivideCurve (controlPointVector, LeftSubVector, RightSubVector) ;
            drawBezierCurve (LeftSubVector,level+1, g) ;
            drawBezierCurve (RightSubVector,level+1, g) ;
        }
    }



    private boolean straightEnough (Point2D.Double[] ControlPointVector) {
// find length around control polygon
        Double d1 = lengthOf(ControlPointVector[0],ControlPointVector[1]) + lengthOf(ControlPointVector[1], ControlPointVector[2]) + lengthOf(ControlPointVector[2], ControlPointVector[3]);
// find doMathOnPoints directly between first and last control point
        Double d2 = lengthOf(ControlPointVector[0],ControlPointVector[3]) ;
        if ( Math.abs(d1 - d2) < 0.0001f )
            return true ;
        else
            return false ;
    }

    private Double lengthOf(Point2D.Double aDouble, Point2D.Double aDouble1) {
        Double answer = null;
        Double X = Math.pow((aDouble.getX()-aDouble1.getX()), 2);
        Double Y = Math.pow((aDouble.getY()-aDouble1.getY()), 2);
        answer = Math.sqrt(X+Y);
        return answer;
    }


    private void subdivideCurve (Point2D.Double[] Q,Point2D.Double[] R, Point2D.Double[] S ) {
        Point2D.Double T = doMathOnPoints(Q[1], Q[2]);
        R[0]=Q[0];
        R[1]= doMathOnPoints(Q[0], Q[1]);
        R[2]= doMathOnPoints(R[1], T);
        S[3]=Q[3];
        S[2]= doMathOnPoints(Q[3], Q[2]);
        S[1]= doMathOnPoints(S[2], T);
        R[3]= doMathOnPoints(R[2], S[1]);
        S[0]=R[3];
    }

    private Point2D.Double doMathOnPoints(Point2D.Double p1, Point2D.Double p2){
        return new Point2D.Double(( p1.getX() / 2.0f + p2.getX() / 2.0f ),
                ( p1.getY() / 2.0f + p2.getY() / 2.0f ));
    }
}
