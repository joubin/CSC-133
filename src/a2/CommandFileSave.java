package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFileSave extends AbstractAction{

    private static CommandFileSave save = null;
    private GameWorldProxy gwp;

    public CommandFileSave(){
        super("Save");
    }

    public void target(GameWorldProxy gwp) {
        this.gwp = gwp;
    }

    public synchronized static CommandFileSave getInstance(){
        if (save == null) save = new CommandFileSave();
        return save;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save From " + e.getActionCommand() + " " + e.getSource().getClass());
    }
}
