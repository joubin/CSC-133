package a2;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 12:16 PM
 */
public class GameWorldProxy implements IObservable, IGameWold {
    GameWorld gw; // only game has knowldge of the real game world object

    public GameWorldProxy(GameWorld gw) {
        this.gw = gw;
    }


    @Override
    public int getClock() {
        return gw.getClock();
    }

    @Override
    public int getPlayerHealth() {
        return gw.getPlayerHealth();
    }

    @Override
    public int getScore() {
        return gw.getScore();
    }

    @Override
    public void tick() {
        gw.tick();
    }

    @Override
    public boolean getSound() {
        return gw.getSound();
    }

    @Override
    public void setSound(boolean sound) {
        // Does nothing for safety
    }


    @Override
    public void addObserver(IObserver observer) {
    }

    @Override
    public void notifyObservers() {
    }

    @Override
    public void getHitWithMissile() {
        // Does nothing for safety
    }

    @Override
    public void changePlayerTankDirection(int d) {
        // Does nothing for safety
    }

    @Override
    public void modifyPlayerTankSpeed(int s) {
        // Does nothing for safety
    }

    @Override
    public void fireEnemyTankMissile() {
        // Does nothing for safety
    }

    @Override
    public void missileCollisions() {
        // Does nothing for safety
    }

    @Override
    public void firePlayerTankMissile() {
        // Does nothing for safety
    }

    @Override
    public void blockMovableObject() {
        // Does nothing for safety
    }

    @Override
    public void toggleStrategy() {
        // Does nothing for safety
    }

    public GameObjectCollection getGameWorldObjects(){
        return gw.getGameWorldObjects();
    }
}
