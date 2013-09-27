package A1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:32 PM
 */
public class GameWorld {
    private ArrayList<GameObject> go = new ArrayList<GameObject>();
    private int clock = 0;
    private Tank myTank;
    private int score;


    public void initialize(int numTank, int numRock, int numTree){
        for(int i = 0; i < numRock; ++i){
            go.add(new Rock(getAllXY()[0], getAllXY()[1]));
        }

        for (int i = 0; i < numTree; ++i){
            go.add(new Tree(getAllXY()[0], getAllXY()[1]));
        }

        for(int i = 0; i < numTank; ++i){
            Tank temp = new Tank(getAllXY()[0], getAllXY()[1]);
            go.add(temp);
        }

        myTank = new Tank(getAllXY()[0], getAllXY()[1], true);
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
            for (int i = 0; i < go.size(); ++i){
                if (go.get(i).getX() == xy[0] || go.get(i).getY() == xy[1]){
                    xy[0] = randGen.nextFloat();
                    xy[1] = randGen.nextFloat();
                }
                checkedAll += 1;
            }
        }
        return xy;
    }

    public void changePlayerTankDirection(int i){
        System.out.println("Setting direction on my tank");
       myTank.changeDirection(i);
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
        for (int i = 0; i < obj.size(); ++i){
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
        for (int i = 0; i < obj.size(); ++i){
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
        for (int i = 0; i < obj.size(); ++i){
            if (obj.get(i) instanceof Tank || obj.get(i) instanceof Missile){
                tmp.add((MovableItem) obj.get(i));
            }
        }
        return tmp;

    }



    public void fireEnemyTankMissile(){
        ArrayList<Tank  > tmp = returnAllTanksFromObject(go);
        Random r = new Random();
        Tank t = null;
        if (tmp.size() > 1){
            do{
                 t = (Tank) tmp.get(r.nextInt(tmp.size()));
            }while(t == myTank);
        }else {
            System.out.println("You are the only tank");
            return;
        }
        boolean ableToFire = t.fireMissle();
        System.out.println(t.getName());
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
        if(!removeMissileFromMap(1)){
            System.out.println("There were no missiles");
        }else{
            randomTank.modifyArmorStrength(-1);
        }
    }

    public boolean removeMissileFromMap(int x){
        /*
        This is a helper function to remove missles from the map
        */
        ArrayList<Missile> m = returnAllMissileFromObject(go);
        if (m.size() >= x){
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
        for(int i = 0; i < tmp.size(); i++){
            tmp.get(i).update();
            deathReaper(tmp.get(i));
        }
    }

    public void deathReaper(MovableItem m){
        int tmpHealth = m.getHealth();
        if (tmpHealth < 1){
            go.remove(m);
        }

    }

    public void displayCurrentGameState() {
        int health = myTank.getHealth();
        System.out.print("Clock:" + clock +
                        "\nScore:" + score +
                        "\nHealth:" + health+"/10\n");

    }

    public void drawMap() {
        for(Iterator<GameObject> gameObject = go.iterator(); gameObject.hasNext();){
            System.out.println(gameObject.next().toString());

        }
    }

    public void printHelpMessage(){
        System.out.println("Help\n" +
                "r: Turn right 5 degrees clockwise\n" +
                "l: Turn left 5 degrees counter-clockwise\n" +
                "i: Increase speed of your tank\n" +
                "k: Decrease speed of your tank\n" +
                "f: Fire missile from your tank\n" +
                "e: Fire missile from enemy tank\n" +
                "1: Indicate that a random tank has been hit by a missile\n" +
                "2: indicates that a collision occurred between two missiles\n" +
                "3: Random tank has colided with a land object and is now blocked\n" +
                "t: Tick game clock\n" +
                "d: Display current game states and values\n" +
                "m: Display map for the current game\n" +
                "q: Quit game\n" +
                "?: Print this menu\n");
    }

    public void quit() {
    System.exit(0);
    }
}
