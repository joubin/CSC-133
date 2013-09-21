package A1;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 9/19/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameWorld {
    private Tank myTank = new Tank();

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

    public void missleCollisions() {
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
