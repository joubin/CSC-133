package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandChangeStrategy extends AbstractAction {

    private static CommandChangeStrategy changeStrategy = null;
    private
    GameWorldProxy gwp;

    public CommandChangeStrategy() {
        super("Enemy Switch");
    }

    public synchronized static CommandChangeStrategy getInstance() {
        if (changeStrategy == null) changeStrategy = new CommandChangeStrategy();
        return changeStrategy;
    }

    public void setTarget(GameWorldProxy gwp) {
        this.gwp = gwp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.toggleStrategy();
        System.out.println("Enemy Swtich From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
