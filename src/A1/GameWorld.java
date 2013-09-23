package A1;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameWorld {
    private ArrayList<GameObject> go;
    private ArrayList<Tree> trees;
    private ArrayList<Rock> rocks;
    private Tank myTank = new Tank();
    private ArrayList<Tank> enemyTank;

    public void initialize(int numTank, int numRock, int numTree){
        for(int i = 0; i <= numRock; ++i){
            go.add(new Rock());
        }

        for (int i = 0; i < numTree; ++i){
            go.add(new Tree());
        }

        for(int i = 0; i <= numTank; ++i){
            go.add(new Tank());
        }



    }

    public boolean getAllXY(ArrayList<GameObject> o, float x, float y){
        for (int i = 0; i <= o.size(); ++i){
          if (x == o.get(i).getX() && y == o.get(i).getY()){
                return true;
            }else{
              return false;
          }
        }
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
