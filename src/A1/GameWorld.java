package A1;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameWorld {
    private ArrayList<GameObject> go;

    private Tank myTank;

    public void initialize(int numTank, int numRock, int numTree){
        for(int i = 0; i <= numRock; ++i){
            go.add(new Rock(getAllXY()[0], getAllXY()[1]));
        }

        for (int i = 0; i < numTree; ++i){
            go.add(new Tree(getAllXY()[0], getAllXY()[1]));
        }

        for(int i = 0; i <= numTank; ++i){
            Tank temp = new Tank(getAllXY()[0], getAllXY()[1]);
            go.add(temp);
        }

        myTank = new Tank(getAllXY()[0], getAllXY()[1]);
        go.add(myTank);

    }

    public float [] getAllXY(){
        /*
        This method ensures that objects dont get placed on top of each other
         */
        Random randGen = new Random(1024);
        float  [] xy = {randGen.nextFloat(),randGen.nextFloat()};

        int checkedAll = 0;
        while(checkedAll != go.size()){
            for (int i = 0; i <= go.size(); ++i){
                if (go.get(i).getX() == xy[0] || go.get(i).getY() == xy[1]){
                    xy[0] = randGen.nextFloat();
                    xy[1] = randGen.nextFloat();
                    checkedAll = 0;
                }
            }
        }
        return xy;
    }

    public void changePlayerTankDirection(int i){
       myTank.setDirection(i);
   }

    public void modifyPlayerTankSpeed(int i){
        myTank.modifySpeed(i);
    }

    public void firePlayerTankMissile(){
        boolean ableToFire = myTank.fireMissle();
        if( ableToFire ){
            Missile m = new Missile(myTank);
            myTank.modifyMissleCount(-1);
            go.add(m);
        }else{
            System.out.println("This tank has no more ammo");
        }
    }

    public ArrayList<GameObject> getObject(ArrayList<GameObject> obj, char typeOf){
        /*
        This returns objects of type 'typeof' in an arrayList.
        This is just a helper function
         */
        ArrayList<GameObject> tmp = null;
        for (int i = 0; i <= obj.size(); ++i){
            GameObject tempObject = obj.get(i);
            switch (typeOf){
                case 'T':  // if T, then looking for Tank
                    if (obj.get(i) instanceof Tank && obj.get(i) != myTank){
                        tmp.add(obj.get(i));
                    }
                    break;
                case 'M': // if M, then looking for a Missile
                    if (obj.get(i) instanceof Missile){
                        tmp.add(obj.get(i));
                    }
                    break;
                default:break;
            }
        }
        return tmp;
    }

    public void fireEnemyTankMissile(){
        ArrayList tmp = getObject(go, 'T');
        Random r = new Random(tmp.size());
        Tank t = (Tank) tmp.get(r.nextInt());
        boolean ableToFire = t.fireMissle();
        if (ableToFire){
            Missile m = new Missile(t);
            t.modifyMissleCount(-1);
            go.add(m);
        }else{
            System.out.println("This tank has no more ammo");
        }


    }

    public void getHitWithMissle() {
        myTank.modifyArmorStrength(-1);
    }

    public boolean removeMissileFromMap(int x){
        /*
        This is a helper function to remove missles from the map
        */
        ArrayList<GameObject> m = getObject(go, 'M');
        if (m.size() > x){
        Random r = new Random(m.size());
        for(int i = 0; i < x; ++i){
            Missile tmp = (Missile) m.get(r.nextInt());
            go.remove(go.indexOf(tmp));
            }
        return true;
        }
        return false;
    }

    public void missileCollisions() {
        boolean didRemoveMissile = removeMissileFromMap(2);
        if (!didRemoveMissile){
            System.out.println("There arent two missiles for two to collide");
        }
    }

    public void blockMovableObject() {
    }

    public void tickClock() {
    }

    public void displayCurrentGameState() {
    }

    public void drawMap() {
    }

    public void printHelpMessage() {
    }

    public void quit() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
