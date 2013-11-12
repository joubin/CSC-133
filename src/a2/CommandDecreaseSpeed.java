package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandDecreaseSpeed extends AbstractAction {
    private static CommandDecreaseSpeed commandIncreaseSpeed = null;
    private GameWorldProxy gameWorldProxy;


    public CommandDecreaseSpeed() {
        super("Increase Speed");
    }

    public synchronized static CommandDecreaseSpeed getInstance() {
        if (commandIncreaseSpeed == null) commandIncreaseSpeed = new CommandDecreaseSpeed();
        return commandIncreaseSpeed;
    }

    public void target(GameWorldProxy gwp) {
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorldProxy.modifyPlayerTankSpeed(-1);

        System.out.println("Down From " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
