package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandGetHitByMissile extends AbstractAction {
    private GameWorldProxy gwp;
    private static CommandGetHitByMissile getHitByMissile = null;

    public synchronized CommandGetHitByMissile getInstance(){
        if (getHitByMissile == null) getHitByMissile = new CommandGetHitByMissile();
        return getHitByMissile;
    }

    public void target(GameWorldProxy gwp){
        this.gwp = gwp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.getHitWithMissile();
        System.out.println("Get hit by missile :. From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
