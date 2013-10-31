package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFireEnemyMissile extends AbstractAction {
    private static CommandFireEnemyMissile commandFire = null;
    private GameWorldProxy gameWorldProxy;

    public CommandFireEnemyMissile() {
        super("Fire");
    }

    public synchronized static CommandFireEnemyMissile getInstance() {
        if (commandFire == null) commandFire = new CommandFireEnemyMissile();
        return commandFire;
    }

    public void target(GameWorldProxy gwp) {
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameWorldProxy == null) System.exit(1);
        gameWorldProxy.fireEnemyTankMissile();
        System.out.println("Fire Missile From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
