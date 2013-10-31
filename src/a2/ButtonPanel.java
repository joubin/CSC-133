package a2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: joubin
 * Date: 10/11/13
 * Time: 8:20 PM
 */
public class ButtonPanel extends JPanel {
    private GameWorldProxy gwp = null;
    public ButtonPanel(GameWorldProxy gwp) {
        this.gwp =  gwp;
        this.setPreferredSize(new Dimension(200, 700));
        this.setLayout(new GridLayout(11, 1));
        this.setBorder(new TitledBorder("Commands"));
        addButtons();
    }

    public void addButtons() {
       /*
       Setup the quit button.
        */
        CommandHelp help = CommandHelp.getInstance();
        CommandAbout about = CommandAbout.getInstance();
        CommandQuit quit = CommandQuit.getInstance();
        CommandTick tick = CommandTick.getInstance();
        CommandGetHitByMissile getHitByMissile = CommandGetHitByMissile.getInstance();
        CommandMissileHitMissile missileHitMissile = CommandMissileHitMissile.getInstance();
        missileHitMissile.target(gwp);
        getHitByMissile.target(gwp);
        tick.target(gwp);
        JButton missileHitMissileButton = new JButton(missileHitMissile);
        JButton tickButton = new JButton(tick);
        JButton gethit = new JButton(getHitByMissile);
        JButton helpButton = new JButton(CommandHelp.getInstance());
        JButton quitButton = new JButton(CommandQuit.getInstance());

        this.add(missileHitMissileButton);
        this.add(gethit);
        this.add(tickButton);
        this.add(helpButton);
        this.add(quitButton);



    }
}
