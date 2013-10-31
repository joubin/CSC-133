package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandUndo extends AbstractAction {

    private static CommandUndo undo = null;
    private GameWorldProxy gwp;

    public CommandUndo() {
        super("Undo");
    }

    public void target(GameWorldProxy gwp) {
        this.gwp = gwp;
    }

    public synchronized static CommandUndo getInstance() {
        if (undo == null) undo = new CommandUndo();
        return undo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}