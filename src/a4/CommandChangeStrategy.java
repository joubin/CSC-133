package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandChangeStrategy extends AbstractAction {

    private static CommandChangeStrategy changeStrategy = null;
    private GameWorld gw;

    public CommandChangeStrategy() {
        super("Enemy Switch");
    }

    public synchronized static CommandChangeStrategy getInstance() {
        if (changeStrategy == null) changeStrategy = new CommandChangeStrategy();
        return changeStrategy;
    }

    public void setTarget(GameWorld gw) {
        this.gw = gw;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gw.toggleStrategy();
        //System.out.println("Enemy Swtich From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
