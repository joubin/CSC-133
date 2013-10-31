package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandDecreaseSpeed extends AbstractAction {
    private static CommandDecreaseSpeed commandDecreaseSpeed = null;
    private GameWorldProxy gameWorldProxy;

    public CommandDecreaseSpeed() {
        super("Increase Speed");
    }

    public synchronized static CommandDecreaseSpeed getInstance(){
        if (commandDecreaseSpeed == null) commandDecreaseSpeed = new CommandDecreaseSpeed();
        return commandDecreaseSpeed;
    }

    public void target(GameWorldProxy gwp){
        this.gameWorldProxy = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorldProxy.modifyPlayerTankSpeed(-1);

        System.out.println("Decrease Speed From " + e.getActionCommand() + " " + e.getSource().getClass());


    }
}
