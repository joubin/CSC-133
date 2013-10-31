package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandBlockTank extends AbstractAction {

    private static CommandBlockTank blockT = null;
    private
    GameWorldProxy gwp;

    public CommandBlockTank() {
        super("Block Tank");
    }

    public synchronized static CommandBlockTank getInstance() {
        if (blockT == null) blockT = new CommandBlockTank();
        return blockT;
    }

    public void setTarget(GameWorldProxy gwp) {
        this.gwp = gwp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.blockMovableObject();
        System.out.println("Block movable From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
