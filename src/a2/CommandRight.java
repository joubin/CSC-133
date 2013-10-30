package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandRight extends AbstractAction {
    private static CommandRight commandRight = null;
    private GameWorldProxy gameWorldProxy;

    public CommandRight() {
        super("Turn Left");
    }

    public synchronized static CommandRight getInstance(){
        if (commandRight == null) commandRight = new CommandRight();
        return commandRight;
    }

    public void target(GameWorldProxy gwp){
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorldProxy.changePlayerTankDirection(5);
        System.out.println("Right command from " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
