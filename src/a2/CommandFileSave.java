package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFileSave extends AbstractAction{

    private static CommandFileSave pFile = null;
    private GameWorldProxy gwp;

    public CommandFileSave(){
        super("File");
    }

    public void target(GameWorldProxy gwp) {
        this.gwp = gwp;
    }

    public synchronized static CommandFileSave getInstance(){
        if (pFile == null) pFile = new CommandFileSave();
        return pFile;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("New File From " + e.getActionCommand() + " " + e.getSource().getClass());
    }
}
