package A1;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
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
    private int clock = 0;
    public enum objectTypes {
        TANK, ROCK, TREE
    }
    private Tank myTank;
    private int score;


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
        Random randGen = new Random();
        float  [] xy = {randGen.nextInt(1024),randGen.nextInt(1024)};

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


    public ArrayList<Tank> returnAllTanksFromObject(ArrayList<GameObject> obj){
        /*
        Returns all tanks that are in obj.
        This is a helper method
         */
        ArrayList<Tank> tmp = new ArrayList<Tank>();
        for (int i = 0; i <= obj.size(); ++i){
            if (obj.get(i) instanceof Tank){
                tmp.add((Tank) obj.get(i));
            }
        }
        return tmp;
        }

    public ArrayList<Missile> returnAllMissileFromObject(ArrayList<GameObject> obj){
        /*
        Returns all missiles that are in obj.
        This is a helper method
         */
        ArrayList<Missile> tmp = new ArrayList<Missile>();
        for (int i = 0; i <= obj.size(); ++i){
            if (obj.get(i) instanceof Missile){
                tmp.add((Missile) obj.get(i));
            }
        }
        return tmp;
    }

    public ArrayList<MovableItem> returnAllMoveableItemsFromObject(ArrayList<GameObject> obj){
        /*
        Returns all Moveable items that are in obj.
        This is a helper method
         */
        ArrayList<MovableItem> tmp = new ArrayList<MovableItem>();
        for (int i = 0; i <= obj.size(); ++i){
            if (obj.get(i) instanceof MovableItem){
                tmp.add((MovableItem) obj.get(i));
            }
        }
        return tmp;

    }



    public void fireEnemyTankMissile(){
        ArrayList<Tank  > tmp = returnAllTanksFromObject(go);
        Random r = new Random();
        Tank t = (Tank) tmp.get(r.nextInt(tmp.size()));
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
        ArrayList<Tank> tanksInGame = returnAllTanksFromObject(go);
        Random r = new Random();
        Tank randomTank = tanksInGame.get(r.nextInt(tanksInGame.size()));
//        ArrayList<Missile> missilesInGame = returnAllMissileFromObject(go);
//        Missile randomMissile = missilesInGame.get(r.nextInt(missilesInGame.size()));
        randomTank.modifyArmorStrength(-1);
        if(!removeMissileFromMap(1)){
            System.out.println("There were no missiles");
        }else{
        myTank.modifyArmorStrength(-1);
        }
    }

    public boolean removeMissileFromMap(int x){
        /*
        This is a helper function to remove missles from the map
        */
        ArrayList<Missile> m = returnAllMissileFromObject(go);
        if (m.size() > x){
        Random r = new Random();
        for(int i = 0; i < x; ++i){
            Missile tmp = (Missile) m.get(r.nextInt(m.size()));
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
        ArrayList<Tank> tanks = returnAllTanksFromObject(go);
        Random r = new Random();
        Tank tmp = (Tank) tanks.get(r.nextInt(tanks.size()));
        tmp.setSpeed(0);
        tmp.toggleBlocked();
    }

    public void tickClock() {
        clock = clock + 1;
        ArrayList<MovableItem> tmp =  returnAllMoveableItemsFromObject(go);
        for(int i = 0; i <= tmp.size(); i++){
            tmp.get(i).update();
        }
    }

    public void displayCurrentGameState() {
        int health = myTank.getArmorStrength();
        System.out.print("Clock:" + clock +
                        "\nScore:" + score +
                        "\nHealth:" + health+"/10\n");

    }

    public void drawMap() {
        for(Iterator<GameObject> gameObject = go.iterator(); gameObject.hasNext();){
            gameObject.next().toString();

        }
    }

    public void printHelpMessage(){
        System.out.println("Help" +
                "r: Turn right 5 degrees clockwise" +
                "l: Turn left 5 degrees counter-clockwise" +
                "i: Increase speed of your tank" +
                "k: Decrease speed of your tank" +
                "f: Fire missile from your tank" +
                "e: Fire missile from enemy tank" +
                "1: Indicate that a random tank has been hit by a missile" +
                "2: indicates that a collision occurred between two missiles" +
                "3: Random tank has colided with a land object and is now blocked" +
                "t: Tick game clock" +
                "d: Display current game states and values" +
                "m: Display map for the current game" +
                "q: Quit game" +
                "?: Print this menu");
    }

    public void quit() {
    System.exit(0);
    }
}
