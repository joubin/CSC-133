package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandIncreaseSpeed extends AbstractAction {
    private static CommandIncreaseSpeed commandIncreaseSpeed = null;
    private GameWorldProxy gameWorldProxy;

    public CommandIncreaseSpeed() {
        super("Increase Speed");
    }

    public synchronized static CommandIncreaseSpeed getInstance(){
        if (commandIncreaseSpeed == null) commandIncreaseSpeed = new CommandIncreaseSpeed();
        return commandIncreaseSpeed;
    }

    public void target(GameWorldProxy gwp){
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorldProxy.modifyPlayerTankSpeed(1);

        System.out.println("About From " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
