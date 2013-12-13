package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandIncreaseSpeed extends AbstractAction {
    private static CommandIncreaseSpeed commandIncreaseSpeed = null;
    private GameWorld gameworld;

    public CommandIncreaseSpeed() {
        super("Increase Speed");
    }

    public synchronized static CommandIncreaseSpeed getInstance() {
        if (commandIncreaseSpeed == null) commandIncreaseSpeed = new CommandIncreaseSpeed();
        return commandIncreaseSpeed;
    }

    public void target(GameWorld gw) {
        this.gameworld = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameworld.modifyPlayerTankSpeed(1);

        System.out.println("Increase Speed From " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
