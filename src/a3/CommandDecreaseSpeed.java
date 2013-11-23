package a3;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandDecreaseSpeed extends AbstractAction {
    private static CommandDecreaseSpeed commandIncreaseSpeed = null;
    private GameWorld gameWorld;


    public CommandDecreaseSpeed() {
        super("Increase Speed");
    }

    public synchronized static CommandDecreaseSpeed getInstance() {
        if (commandIncreaseSpeed == null) commandIncreaseSpeed = new CommandDecreaseSpeed();
        return commandIncreaseSpeed;
    }

    public void target(GameWorld gw) {
        this.gameWorld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.modifyPlayerTankSpeed(-1);

        System.out.println("Down From " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
