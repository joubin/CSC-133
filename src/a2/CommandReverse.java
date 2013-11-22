package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandReverse extends AbstractAction {
    private static CommandReverse commandRe = null;
    private GameWorld gameWorld;


    public CommandReverse() {
        super("Reverse");
    }

    public synchronized static CommandReverse getInstance() {
        if (commandRe == null) commandRe = new CommandReverse();
        return commandRe;
    }

    public void target(GameWorld gw) {
        this.gameWorld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.reverseAll();

        System.out.println("CommandReverse " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
