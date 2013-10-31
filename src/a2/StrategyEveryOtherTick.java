package a2;

/**
 * User: joubin
 */
public class StrategyEveryOtherTick implements IStrategy {
    GameWorld gameWord;

    public StrategyEveryOtherTick(GameWorld gameWorld) {
        this.gameWord = gameWorld;
    }

    @Override
    public void apply() {
        if (gameWord.getClock()% 2 == 0) gameWord.fireEnemyTankMissile();
    }
}
