package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFireMissile extends AbstractAction {
    private static CommandFireMissile commandFire = null;
    private GameWorldProxy gameWorldProxy;

    public CommandFireMissile() {
        super("Fire");
    }

    public synchronized static CommandFireMissile getInstance(){
        if (commandFire == null) commandFire = new CommandFireMissile();
        return commandFire;
    }

    public void target(GameWorldProxy gwp){
        this.gameWorldProxy = gwp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameWorldProxy == null) System.exit(1);
        gameWorldProxy.firePlayerTankMissile();
        System.out.println("Fire Missile From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
