package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandSound extends AbstractAction {
    GameWorldProxy gwProxy;
    private static CommandSound soundCommand = null;

    public CommandSound(){
        super("Sound");
    }

    public void target(GameWorldProxy gwProxy){
        this.gwProxy = gwProxy;
    }


    public synchronized static CommandSound getInstance() {
        if (soundCommand == null) soundCommand = new CommandSound();
        return soundCommand;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwProxy.setSound(!gwProxy.getSound());
        System.out.println("Sound Command From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
