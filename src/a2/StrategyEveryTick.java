package a2;

/**
 * User: joubin
 */
public class StrategyEveryTick implements IStrategy {
    GameWorld gameWord;

    public StrategyEveryTick(GameWorld gameWorld) {
        this.gameWord = gameWorld;
    }

    @Override
    public void apply() {
        gameWord.fireEnemyTankMissile();
    }
}
