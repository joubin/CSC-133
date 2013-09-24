package A1;

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
            go.add(new Rock());
        }

        for (int i = 0; i < numTree; ++i){
            go.add(new Tree());
        }

        for(int i = 0; i <= numTank; ++i){
            go.add(new Tank(getAllXY()[0], getAllXY()[1]));
        }

        myTank = new Tank(getAllXY()[0], getAllXY()[1]);

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
        myTank.fireMissle();
    }

    public void fireEnemyTankMissile(){
        for (int i = 0; i < go.size(); ++i){
            if (go.get(i) instanceof Tank && go.get(i) != myTank){
                ((Tank) go.get(i)).fireMissle();
            }
        }

    }

    public void getHitWithMissle() {
        //To change body of created methods use File | Settings | File Templates.

    }

    public void missileCollisions() {
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
