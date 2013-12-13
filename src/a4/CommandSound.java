package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandSound extends AbstractAction {
    private GameWorld gameWorld;
    private static CommandSound soundCommand = null;

    public CommandSound() {
        super("Sound");
    }

    public void target(GameWorld gameInstance) {
        this.gameWorld = gameInstance;
    }


    public synchronized static CommandSound getInstance() {
        if (soundCommand == null) soundCommand = new CommandSound();
        return soundCommand;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.setSound(!gameWorld.getSound());
        System.out.println("Sound Command From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
