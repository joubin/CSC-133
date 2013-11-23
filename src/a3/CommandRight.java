package a3;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandRight extends AbstractAction {
    private static CommandRight commandRight = null;
    private GameWorld gameWorld;

    public CommandRight() {
        super("Turn Left");
    }

    public synchronized static CommandRight getInstance() {
        if (commandRight == null) commandRight = new CommandRight();
        return commandRight;
    }

    public void target(GameWorld gw) {
        this.gameWorld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.changePlayerTankDirection(5);
        System.out.println("Right command from " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
