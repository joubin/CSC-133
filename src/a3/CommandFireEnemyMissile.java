package a3;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFireEnemyMissile extends AbstractAction {
    private static CommandFireEnemyMissile commandFire = null;
    private GameWorld gameWorld;

    public CommandFireEnemyMissile() {
        super("Fire");
    }

    public synchronized static CommandFireEnemyMissile getInstance() {
        if (commandFire == null) commandFire = new CommandFireEnemyMissile();
        return commandFire;
    }

    public void target(GameWorld gw) {
        this.gameWorld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameWorld == null) System.exit(1);
        gameWorld.fireEnemyTankMissile();
        System.out.println("Fire Missile From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
