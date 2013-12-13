package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFireMissile extends AbstractAction {
    private static CommandFireMissile commandFire = null;
    private GameWorld gameworld;

    public CommandFireMissile() {
        super("Fire");
    }

    public synchronized static CommandFireMissile getInstance() {
        if (commandFire == null) commandFire = new CommandFireMissile();
        return commandFire;
    }

    public void target(GameWorld gw) {
        this.gameworld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameworld == null) System.exit(1);
        gameworld.firePlayerTankMissile();
        System.out.println("Fire Missile From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
