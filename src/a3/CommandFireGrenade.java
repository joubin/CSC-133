package a3;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFireGrenade extends AbstractAction {
    private static CommandFireGrenade commandFire = null;
    private GameWorld gameworld;

    public CommandFireGrenade() {
        super("Fire Grenade");
    }

    public synchronized static CommandFireGrenade getInstance() {
        if (commandFire == null) commandFire = new CommandFireGrenade();
        return commandFire;
    }

    public void target(GameWorld gw) {
        this.gameworld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameworld == null) System.exit(1);
        gameworld.firePlayerTankGrenade();
        System.out.println("Fire Missile From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
