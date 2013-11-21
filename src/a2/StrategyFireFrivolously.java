package a2;

/**
 * User: joubin
 */
public class StrategyFireFrivolously implements IStrategy {
    GameWorld gameWord;

    public StrategyFireFrivolously(GameWorld gameWorld) {
        this.gameWord = gameWorld;
    }

    @Override
    public void apply() {
        if ((gameWord.getClock()%300)==0)
            gameWord.fireEnemyTankMissile();
    }
}
