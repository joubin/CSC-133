package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandMissileHitMissile extends AbstractAction {

    private static CommandMissileHitMissile missileHitMissile = null;
    private GameWorld gameworld;

    public CommandMissileHitMissile() {
        super("Missile Hit Missile");
    }

    public synchronized static CommandMissileHitMissile getInstance() {
        if (missileHitMissile == null) missileHitMissile = new CommandMissileHitMissile();
        return missileHitMissile;
    }


    public void setTarget(GameWorld gw) {
        this.gameworld = gw;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gameworld.missileCollisions();
        System.out.println("Missile collide from " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
