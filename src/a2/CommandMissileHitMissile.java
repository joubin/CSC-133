package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: joubin
 */
public class CommandMissileHitMissile extends AbstractAction {

    private static CommandMissileHitMissile missileHitMissile = null;
    private GameWorldProxy gwp;

    public CommandMissileHitMissile(){
        super("Missile Hit Missile");
    }

    public synchronized static CommandMissileHitMissile getInstance(){
        if (missileHitMissile == null) missileHitMissile = new CommandMissileHitMissile();
        return missileHitMissile;
    }


    public void setTarget(GameWorldProxy gwp){
        this.gwp = gwp;
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        gwp.missileCollisions();
        System.out.println("Missile collide from " + e.getActionCommand() + " " + e.getSource().getClass());

    }
}
