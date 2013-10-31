package a2;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 10:18 PM
 */
public interface IGameWold {
    public int getClock();

    public int getPlayerHealth();

    public int getScore();

    public void tick();

    public boolean getSound();

    public void setSound(boolean sound);

    public void addObserver(IObserver obs);

    public void notifyObservers();

    public void getHitWithMissile();

    public void changePlayerTankDirection(int d);

    public void modifyPlayerTankSpeed(int s);

    public void fireEnemyTankMissile();

    public void missileCollisions();

    public void firePlayerTankMissile();

    public void blockMovableObject();

    public void toggleStrategy();

}
