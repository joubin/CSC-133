package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFirePlasma extends AbstractAction {
    private static CommandFirePlasma commandFire = null;
    private GameWorld gameworld;

    public CommandFirePlasma() {
        super("Fire Plasma");
    }

    public synchronized static CommandFirePlasma getInstance() {
        if (commandFire == null) commandFire = new CommandFirePlasma();
        return commandFire;
    }

    public void target(GameWorld gw) {
        this.gameworld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameworld == null) System.exit(1);
        gameworld.firePlayerTankPlasma();
        System.out.println("Fire Plasma From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
