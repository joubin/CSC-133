package a4;

/**
 * User: joubin
 */
public class StrategyFireConservative implements IStrategy {
    GameWorld gameWord;

    public StrategyFireConservative(GameWorld gameWorld) {
        this.gameWord = gameWorld;
    }

    @Override
    public void apply() {
        if ((gameWord.getClock() % 600) == 0) gameWord.fireEnemyTankMissile();
    }
}
