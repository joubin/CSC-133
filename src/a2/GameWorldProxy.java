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
        gw.setSound(!gw.getSound());
    }


    @Override
    public void addObserver(IObserver observer) {
    }

    @Override
    public void notifyObservers() {
    }

    @Override
    public void getHitWithMissile() {
        gw.getHitWithMissile();
    }

    @Override
    public void changePlayerTankDirection(int d) {
        gw.changePlayerTankDirection(d);
    }

    @Override
    public void modifyPlayerTankSpeed(int s) {
        gw.modifyPlayerTankSpeed(s);
    }

    @Override
    public void fireEnemyTankMissile() {
        gw.fireEnemyTankMissile();
    }

    @Override
    public void missileCollisions() {
        gw.missileCollisions();
    }

    @Override
    public void firePlayerTankMissile() {
        gw.firePlayerTankMissile();
    }

    @Override
    public void blockMovableObject() {
        gw.blockMovableObject();
    }

    @Override
    public void toggleStrategy() {
        gw.toggleStrategy();
    }


}
