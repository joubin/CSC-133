package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandPause extends AbstractAction {
    private static CommandPause commandPause = null;
    private GameWorld gameWorld;


    public CommandPause() {
        super("Pause");
    }

    public synchronized static CommandPause getInstance() {
        if (commandPause == null) commandPause = new CommandPause();
        return commandPause;
    }

    public void target(GameWorld gw) {
        this.gameWorld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.toggleTimer();


        System.out.println("CommandReverse " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
