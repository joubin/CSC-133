package a4;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandGetHitByMissile extends AbstractAction {

    private static CommandGetHitByMissile getHitByMissile = null;
    private GameWorld gw;

    public CommandGetHitByMissile() {
        super("Missile Hit Player");
    }

    public synchronized static CommandGetHitByMissile getInstance() {
        if (getHitByMissile == null) getHitByMissile = new CommandGetHitByMissile();
        return getHitByMissile;
    }

    public void setTarget(GameWorld gw) {
        this.gw = gw;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gw.getHitWithMissile();
        System.out.println("Get hit by missile :. From " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
