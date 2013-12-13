package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandBlockTank extends AbstractAction {

    private static CommandBlockTank blockT = null;
    private GameWorld gw;

    public CommandBlockTank() {
        super("Block Tank");
    }

    public synchronized static CommandBlockTank getInstance() {
        if (blockT == null) blockT = new CommandBlockTank();
        return blockT;
    }

    public void setTarget(GameWorld gw) {
        this.gw = gw;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gw.blockMovableObject();
        System.out.println("Block movable From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
