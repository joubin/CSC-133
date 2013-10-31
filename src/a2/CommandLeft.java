package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandLeft extends AbstractAction {
    private static CommandLeft commandLeft = null;
    private GameWorldProxy gameWorldProxy;

    public CommandLeft() {
        super("Turn Left");
    }

    public synchronized static CommandLeft getInstance() {
        if (commandLeft == null) commandLeft = new CommandLeft();
        return commandLeft;
    }

    public void target(GameWorldProxy gwp) {
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorldProxy.changePlayerTankDirection(-5);
        System.out.println("Left Command From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
