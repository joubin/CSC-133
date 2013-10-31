package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandFileNew extends AbstractAction{

    private static CommandFileNew pFile = null;
    private GameWorldProxy gwp;

    public CommandFileNew(){
        super("File");
    }

    public void target(GameWorldProxy gwp) {
        this.gwp = gwp;
    }

    public synchronized static CommandFileNew getInstance(){
        if (pFile == null) pFile = new CommandFileNew();
        return pFile;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("New File From " + e.getActionCommand() + " " + e.getSource().getClass());
    }
}
